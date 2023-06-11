package ua.nure.liapota.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.nure.liapota.annotations.Authorize;
import ua.nure.liapota.models.data.*;
import ua.nure.liapota.models.util.MappingTableRow;
import ua.nure.liapota.services.*;

import java.util.ArrayList;
import java.util.List;


@Authorize("data,gl-pr-mapping")
@RestController
@RequestMapping("/glPrMapping")
@CrossOrigin(origins = "http://localhost:4200")
public class GlRpMappingController {
    private final GlRpMappingService service;
    private final ValueTypeService valueTypeService;
    private final DepartmentElementService departmentElementService;
    private final CostCenterService costCenterService;
    private final AccountService accountService;

    @Autowired
    public GlRpMappingController(@Qualifier("glRpMappingService") GlRpMappingService service,
                                 ValueTypeService valueTypeService,
                                 DepartmentElementService departmentElementService,
                                 CostCenterService costCenterService,
                                 AccountService accountService) {
        this.service = service;
        this.valueTypeService = valueTypeService;
        this.departmentElementService = departmentElementService;
        this.costCenterService = costCenterService;
        this.accountService = accountService;
    }

    @GetMapping("/accountTypes")
    public ResponseEntity<List<ValueTypeEntity>> getAccountTypes(@RequestParam(name = "mapped") boolean mapped,
                                                                 @RequestParam(name = "facilityId") Integer facilityId) {
        List<ValueTypeEntity> valueTypes;

        if (mapped) {
            valueTypes = valueTypeService.getMappedValueTypes(facilityId);
        } else {
            valueTypes = valueTypeService.getAll();
        }

        return new ResponseEntity<>(valueTypes, HttpStatus.OK);
    }

    @GetMapping("/accounts")
    public ResponseEntity<List<Account>> getAccounts(@RequestParam(name = "valueTypeId") Integer valueTypeId,
                                                     @RequestParam(name = "mapped") boolean mapped,
                                                     @RequestParam(name = "facilityId") Integer facilityId) {
        List<Account> accounts;

        if (valueTypeId == 0) {
            if (mapped) {
                accounts = accountService.getMapped(facilityId);
            } else {
                accounts = accountService.getByFacilityId(facilityId);
            }
        } else {
            accounts = accountService.getByValueType(valueTypeId, facilityId, mapped);
        }

        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    @GetMapping("/costCenters")
    public ResponseEntity<List<CostCenter>> getCostCenters(@RequestParam(name = "valueTypeId") Integer valueTypeId,
                                                           @RequestParam(name = "mapped") boolean mapped,
                                                           @RequestParam(name = "code") String accountCode,
                                                           @RequestParam(name = "facilityId") Integer facilityId) {
        List<CostCenter> costCenters;

        if (valueTypeId == 0) {
            if (accountCode.equals("")) {
                if (mapped) {
                    costCenters = costCenterService.getMapped(facilityId);
                } else {
                    costCenters = costCenterService.getByFacility(facilityId);
                }
            } else {
                costCenters = costCenterService.getByAccountCode(accountCode, facilityId, mapped);
            }
        } else {
            if (accountCode.equals("")) {
                costCenters = costCenterService.getByValueTYpe(valueTypeId, facilityId, mapped);
            } else {
                costCenters = costCenterService.getByAccountCodeValueTypeId(valueTypeId,
                        accountCode,
                        facilityId,
                        mapped);
            }
        }

        return new ResponseEntity<>(costCenters, HttpStatus.OK);
    }

    @GetMapping("/mappings")
    public ResponseEntity<List<MappingTableRow>> getMappings(@RequestParam(name = "valueTypeId") Integer valueTypeId,
                                                             @RequestParam(name = "mapped") boolean mapped,
                                                             @RequestParam(name = "code") String accountCode,
                                                             @RequestParam(name = "costCenter") String costCenterNumber) {
        List<GlRpMapping> mappings;

        if (valueTypeId == 0) {
            if (accountCode.equals("")) {
                if (costCenterNumber.equals("")) {
                    mappings = service.getByMapping(mapped);
                } else {
                    mappings = service.getByCostCenter(costCenterNumber, mapped);
                }
            } else {
                if (costCenterNumber.equals("")) {
                    mappings = service.getByAccount(accountCode, mapped);
                } else {
                    mappings = service.getByCostCenterAccount(accountCode, costCenterNumber, mapped);
                }
            }
        } else {
            if (accountCode.equals("")) {
                if (costCenterNumber.equals("")) {
                    mappings = service.getByValueType(valueTypeId, mapped);
                } else {
                    mappings = service.getByCostCenterType(valueTypeId, costCenterNumber, mapped);
                }
            } else {
                if (costCenterNumber.equals("")) {
                    mappings = service.getByAccountType(valueTypeId, accountCode, mapped);
                } else {
                    mappings = service.getByCostCenterAccountType(valueTypeId,
                            accountCode,
                            costCenterNumber,
                            mapped);
                }
            }
        }

        List<MappingTableRow> mappingTableRows = new ArrayList<>();

        for (GlRpMapping m : mappings) {
            MappingTableRow mappingTableRow = new MappingTableRow(m.getId(),
                    m.getCostCenter(),
                    m.getAccount(),
                    m.getValueType(),
                    m.getDepartmentElement());
            mappingTableRows.add(mappingTableRow);
        }

        return new ResponseEntity<>(mappingTableRows, HttpStatus.OK);
    }

    @GetMapping("/departmentElements/{id}")
    public ResponseEntity<List<DepartmentElement>> getDepartmentElements(@PathVariable Integer id) {
        return new ResponseEntity<>(departmentElementService.getByStandard(id), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Void> updateMapping(@RequestParam(name = "mappingId") Integer mappingId,
                                              @RequestParam(name = "departmentElementId") Integer departmentElementId) {
        service.update(service.getById(mappingId), departmentElementService.getById(departmentElementId));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
