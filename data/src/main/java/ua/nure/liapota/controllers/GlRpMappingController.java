package ua.nure.liapota.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.nure.liapota.annotations.Authorize;
import ua.nure.liapota.models.data.*;
import ua.nure.liapota.models.util.MappingTableRow;
import ua.nure.liapota.services.DepartmentElementService;
import ua.nure.liapota.services.GlRpMappingService;
import ua.nure.liapota.services.ValueTypeService;

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

    @Autowired
    public GlRpMappingController(@Qualifier("glRpMappingService") GlRpMappingService service,
                                 ValueTypeService valueTypeService,
                                 DepartmentElementService departmentElementService) {
        this.service = service;
        this.valueTypeService = valueTypeService;
        this.departmentElementService = departmentElementService;
    }

    @GetMapping("/accountTypes")
    public ResponseEntity<List<ValueTypeEntity>> getAccountTypes() {
        return new ResponseEntity<>(valueTypeService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/accounts")
    public ResponseEntity<List<Account>> getAccounts(@RequestParam(name = "valueTypeId") Integer valueTypeId,
                                                     @RequestParam(name = "mapped") boolean mapped) {
        List<GlRpMapping> mappings = service.getByValueType(valueTypeId, mapped);
        List<Account> accounts = new ArrayList<>();

        for (GlRpMapping m : mappings) {
            accounts.add(m.getAccount());
        }

        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    @GetMapping("/costCenters")
    public ResponseEntity<List<CostCenter>> getCostCenters(@RequestParam(name = "valueTypeId") Integer valueTypeId,
                                                           @RequestParam(name = "mapped") boolean mapped,
                                                           @RequestParam(name = "code") String accountCode) {
        List<GlRpMapping> mappings = service.getByAccountType(valueTypeId, accountCode, mapped);
        List<CostCenter> costCenters = new ArrayList<>();

        for (GlRpMapping m : mappings) {
            costCenters.add(m.getCostCenter());
        }

        return new ResponseEntity<>(costCenters, HttpStatus.OK);
    }

    @GetMapping("/mappings")
    public ResponseEntity<List<MappingTableRow>> getMappings(@RequestParam(name = "valueTypeId") Integer valueTypeId,
                                                             @RequestParam(name = "mapped") boolean mapped,
                                                             @RequestParam(name = "code") String accountCode,
                                                             @RequestParam(name = "costCenter") String costCenterNumber) {
        List<GlRpMapping> mappings = service.getByCostCenterAccountType(valueTypeId,
                accountCode,
                costCenterNumber,
                mapped);
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
