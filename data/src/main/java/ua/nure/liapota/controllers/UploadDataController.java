package ua.nure.liapota.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ua.nure.liapota.annotations.Authorize;
import ua.nure.liapota.models.data.Account;
import ua.nure.liapota.models.data.CostCenter;
import ua.nure.liapota.enumeration.FileTypeEnum;
import ua.nure.liapota.models.data.TimePeriodFacility;
import ua.nure.liapota.models.file.FileEntity;
import ua.nure.liapota.models.util.ErrorMessages;
import ua.nure.liapota.services.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Authorize("data,data-configuration")
@RestController
@RequestMapping("/dataLoad")
@CrossOrigin(origins = "http://localhost:4200")
public class UploadDataController {
    private final FileService fileService;
    private final UploadLogService uploadLogService;
    private final DataLoadService dataLoadService;
    private final CostCenterService costCenterService;
    private final AccountService accountService;
    private final CustomerService customerService;
    private final FacilityService facilityService;
    private String[][] fileToArray;
    private FileEntity fileEntity;
    private TimePeriodFacility timePeriod;
    private String userId;

    @Autowired
    public UploadDataController(FileService fileService,
                                UploadLogService uploadLogService,
                                DataLoadService dataLoadService,
                                CostCenterService costCenterService,
                                AccountService accountService,
                                CustomerService customerService,
                                FacilityService facilityService) {
        this.fileService = fileService;
        this.uploadLogService = uploadLogService;
        this.dataLoadService = dataLoadService;
        this.costCenterService = costCenterService;
        this.accountService = accountService;
        this.customerService = customerService;
        this.facilityService = facilityService;
    }

    @PostMapping
    public ResponseEntity<List<ErrorMessages>> upload(@RequestParam(name = "file") MultipartFile file,
                                               @RequestParam(name = "fileData") FileEntity fileEntity,
                                               @RequestParam(name = "timePeriod")TimePeriodFacility timePeriod,
                                               @RequestParam(name = "dataSet") String dataSet,
                                               HttpServletRequest request)
            throws JsonProcessingException {
        this.timePeriod = timePeriod;
        this.userId = (String) request.getAttribute("userId");
        this.fileEntity = fileEntity;

        try {
            fileService.uploadFile(file,
                    customerService.getById((Integer) request.getAttribute("customerId")),
                    userId,
                    facilityService.getById(timePeriod.getFacilityId()),
                    timePeriod,
                    dataSet,
                    fileEntity);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.fileToArray = fileService.getFileToArray(fileEntity.getFilePath(), fileEntity.getColumnDelimiter());
        List<ErrorMessages> errorMessages = uploadLogService.errorMessageArray(fileToArray,
                fileEntity,
                timePeriod);
        ResponseEntity<List<ErrorMessages>> response;

        if (errorMessages.size() != 0) {
            response = new ResponseEntity<>(errorMessages, HttpStatus.OK);
        } else {
            saveData();
            response = new ResponseEntity<>(HttpStatus.OK);
        }

        return response;
    }

    private void saveData() throws JsonProcessingException {
        String fileType = fileEntity.getFileMapping().getFileType().getName();
        Map<Integer, String> jsonMap = fileEntity.getFileMapping().getMap();
        if (fileType.equals(FileTypeEnum.COST_CENTER_LIST.getFileTypeName())) {
            CostCenter costCenter = new CostCenter();
            costCenter.setFacilityID(timePeriod.getFacilityId());
            costCenter.setAddedBy(userId);
            List<CostCenter> costCenters = dataLoadService.getCostCentersFromMapping(jsonMap,
                    fileToArray,
                    costCenter);
            for (CostCenter c : costCenters) {
                costCenterService.create(c);
            }
        } else if (fileType.equals(FileTypeEnum.GENERAL_LEDGER_ACCOUNTS_LIST.getFileTypeName()) ||
                fileType.equals(FileTypeEnum.PAYROLL_ACCOUNTS_LIST.getFileTypeName())) {
            Account account = new Account();
            account.setFacilityId(timePeriod.getFacilityId());
            account.setAddedBy(userId);
            List<Account> accounts = dataLoadService.getAccountsFromMapping(jsonMap,
                    fileToArray,
                    account);
            for (Account a : accounts) {
                accountService.create(a);
            }
        } else if (fileType.equals(FileTypeEnum.GENERAL_LEDGER_DATA.getFileTypeName())) {
            dataLoadService.loadGeneralLedgerData(jsonMap, fileToArray, timePeriod);
        } else if (fileType.equals(FileTypeEnum.PAYROLL_DATA.getFileTypeName())) {
            dataLoadService.loadPayrollData(jsonMap, fileToArray, timePeriod);
        }
    }
}
