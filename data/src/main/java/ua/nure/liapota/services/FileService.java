package ua.nure.liapota.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ua.nure.liapota.enumeration.FileTypeEnum;
import ua.nure.liapota.models.data.TimePeriodFacility;
import ua.nure.liapota.models.file.FileEntity;
import ua.nure.liapota.models.security.Customer;
import ua.nure.liapota.models.security.Facility;
import ua.nure.liapota.repositories.file.FileRepository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileService extends EntityService<FileEntity, Integer, FileRepository> {
    @Autowired
    public FileService(FileRepository repository) {
        this.repository = repository;
    }

    public String[][] getFileToArray(String filePath, char stringDelimiter) {
        List<String[]> data = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] columns = line.split(String.valueOf(stringDelimiter));
                data.add(columns);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[][] dataArray = new String[data.size()][];
        return data.toArray(dataArray);
    }

    public void uploadFile(MultipartFile file,
                             Customer customer,
                             String userId,
                             Facility facility,
                             TimePeriodFacility timePeriod,
                             String dataSet,
                             FileEntity fileData) throws IOException {
        Path targetFile = null;

        if (!file.isEmpty()) {
            Path customerDirectory = Paths.get(customer.getName());

            if (Files.notExists(customerDirectory)) {
                Files.createDirectory(customerDirectory);
            }

            Path userDirectory = customerDirectory.resolveSibling(userId);

            if (Files.notExists(userDirectory)) {
                Files.createDirectory(userDirectory);
            }

            Path facilityDirectory = userDirectory.resolveSibling(facility.getName());

            if (Files.notExists(facilityDirectory)) {
                Files.createDirectory(facilityDirectory);
            }

            String fileTypeName = fileData.getFileMapping().getFileType().getName();

            if (fileTypeName.equals(FileTypeEnum.COST_CENTER_LIST.getFileTypeName()) ||
            fileTypeName.equals(FileTypeEnum.GENERAL_LEDGER_ACCOUNTS_LIST.getFileTypeName()) ||
            fileTypeName.equals(FileTypeEnum.PAYROLL_ACCOUNTS_LIST.getFileTypeName())) {
                Path initialDirectory = facilityDirectory.resolveSibling("initial");

                if (Files.notExists(initialDirectory)) {
                    Files.createDirectory(initialDirectory);
                }

                Path filePath = initialDirectory.resolveSibling(fileTypeName);

                if (Files.notExists(filePath)) {
                    Files.createFile(filePath);
                }

                targetFile = filePath;
            } else {
                Path dataSetDirectory = facilityDirectory.resolveSibling(timePeriod.getTimePeriod().getShortName());

                if (Files.notExists(dataSetDirectory)) {
                    Files.createDirectory(dataSetDirectory);
                }

                Path dataSetPath;

                if (dataSet != null) {
                    dataSetPath = dataSetDirectory.resolveSibling(dataSet);
                } else {
                    dataSetPath = dataSetDirectory.resolveSibling(file.getOriginalFilename());
                }


                if (Files.notExists(dataSetPath)) {
                    Files.createFile(dataSetPath);
                }

                targetFile = dataSetPath;
            }

            byte[] data = file.getBytes();
            Files.write(targetFile, data);
        }

        fileData.setFilePath(targetFile.toString());
        fileData.setUploadBy(userId);
        repository.save(fileData);
    }
}
