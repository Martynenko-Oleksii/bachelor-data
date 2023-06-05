package ua.nure.liapota.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.nure.liapota.enumeration.Column;
import ua.nure.liapota.enumeration.FileTypeEnum;
import ua.nure.liapota.models.data.TimePeriodFacility;
import ua.nure.liapota.models.file.FileEntity;
import ua.nure.liapota.models.file.FileType;
import ua.nure.liapota.models.file.UploadLog;
import ua.nure.liapota.models.util.ErrorMessages;
import ua.nure.liapota.repositories.file.UploadLogRepository;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class UploadLogService extends EntityService<UploadLog, Integer, UploadLogRepository> {
    private FileType fileType;

    @Autowired
    public UploadLogService(UploadLogRepository repository) {
        this.repository = repository;
    }

    public List<ErrorMessages> errorMessageArray(String[][] fileInArray,
                                                 FileEntity fileData,
                                                 TimePeriodFacility timePeriod) throws JsonProcessingException {
        this.fileType = fileData.getFileMapping().getFileType();
        List<ErrorMessages> errorMessages = new ArrayList<>();
        String[] lineErrorMessages = new String[fileInArray[0].length];
        int errorLines = 0;

        for (String[] strings : fileInArray) {
            for (Map.Entry<Integer, String> entry : fileData.getFileMapping().getMap().entrySet()) {
                boolean haveErrors = false;
                int key = entry.getKey();
                String value = entry.getValue();

                try {
                    lineErrorMessages[key - 1] = getFieldError(strings[key - 1], value);

                    if (!lineErrorMessages[key - 1].equals("")) {
                        haveErrors = true;
                    } else if (isUnique(fileInArray, value, key - 1)) {
                        lineErrorMessages[key - 1] = "must be unique";
                        haveErrors = true;
                    }
                } catch (IndexOutOfBoundsException e) {
                    lineErrorMessages[key - 1] = "don`t have COLUMN " + key;
                    haveErrors = true;
                }

                if (haveErrors) {
                    errorLines++;
                }
            }

            errorMessages.add(new ErrorMessages(strings, lineErrorMessages));
        }

        UploadLog log = new UploadLog();

        if (errorLines == 0) {
            errorMessages = null;
            log.setStatus("error");
        } else {
            log.setStatus("correct");
        }

        log.setErrorLinesCount(errorLines);
        log.setFacilityId(timePeriod.getFacilityId());
        log.setFileType(fileData.getFileMapping().getFileType());
        log.setFileName(fileData.getFilePath());
        log.setUploadBy(fileData.getUploadBy());
        log.setLinesCount(fileInArray.length);
        log.setUploadDate(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        log.setMappingName(fileData.getFileMapping().getName());
        log.setTimePeriod(timePeriod.getTimePeriod().getShortName());
        repository.save(log);

        return errorMessages;
    }

    private boolean isUnique(String[][] fileInArray, String mapField, int key) {
        boolean result = true;

        if ((mapField.equals(Column.ACCOUNT_CODE.getColumnName()) &&
                (fileType.getName().equals(FileTypeEnum.GENERAL_LEDGER_ACCOUNTS_LIST.getFileTypeName()) ||
                fileType.getName().equals(FileTypeEnum.PAYROLL_ACCOUNTS_LIST.getFileTypeName()))) ||
                (mapField.equals(Column.COST_CENTER_NUMBER.getColumnName()) &&
                        (fileType.getName().equals(FileTypeEnum.COST_CENTER_LIST.getFileTypeName())))) {
            String save = "";
            for (int i = 0; i < fileInArray.length; i++) {
                if (save.equals(fileInArray[i][key])) {
                    result = false;
                    i = fileInArray.length;
                }
            }
        }

        return result;
    }

    private String getFieldError(String factField, String mapField) {
        String fieldError = "";
        Column columnRules = getByColumnName(mapField);

        if (columnRules != null) {

            if (factField.length() > columnRules.getLength()) {
                fieldError = "must be shorter then " + columnRules.getLength();
            } else {
                switch (columnRules.getValueType()) {
                    case DOUBLE:

                        try {
                            Double.parseDouble(factField);
                        } catch (NumberFormatException e) {
                            fieldError = "must be plain double";
                        }
                        break;
                    case STRING:
                        break;
                    case GENERAL_LEDGER_ACCOUNT_TYPE:
                    case PAYROLL_ACCOUNT_TYPE: {
                        boolean correct = false;
                        char[] chars = columnRules.getValueType().getCharArrayValue();
                        for (char c : chars) {

                            if (factField.equals(String.valueOf(c))) {
                                correct = true;
                                break;
                            }
                        }

                        if (!correct) {
                            fieldError = "must be one of these: "
                                    + String.join(", ", convertCharArrayToStringArray(chars));
                        }
                        break;
                    }
                }
            }
        }

        return fieldError;
    }

    private static String[] convertCharArrayToStringArray(char[] charArray) {
        String[] stringArray = new String[charArray.length];
        for (int i = 0; i < charArray.length; i++) {
            stringArray[i] = String.valueOf(charArray[i]);
        }
        return stringArray;
    }

    private Column getByColumnName(String columnName) {
        for (Column column : Column.values()) {
            String factColumnName = column.getColumnName();
            if (factColumnName.equals(columnName)) {
                if (factColumnName.equals(Column.GENERAL_LEDGER_ACCOUNT_TYPE.getColumnName()) ||
                        factColumnName.equals(Column.PAYROLL_ACCOUNT_TYPE.getColumnName())) {
                    if (fileType.getName().equals(FileTypeEnum.GENERAL_LEDGER_ACCOUNTS_LIST.getFileTypeName())) {
                        return Column.GENERAL_LEDGER_ACCOUNT_TYPE;
                    } else {
                        return Column.PAYROLL_ACCOUNT_TYPE;
                    }
                }
                return column;
            }
        }
        return null;
    }
}
