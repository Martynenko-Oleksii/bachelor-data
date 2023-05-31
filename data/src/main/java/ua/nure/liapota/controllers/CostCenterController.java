package ua.nure.liapota.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.nure.liapota.models.data.CostCenter;
import ua.nure.liapota.services.CostCenterService;

@RestController
@RequestMapping("/costCenters")
@CrossOrigin(origins = "http://localhost:4200")
public class CostCenterController {
    CostCenterService service;

    public CostCenterController(CostCenterService service){
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<CostCenter> create(@RequestBody CostCenter costCenter) {
        return new ResponseEntity<>(service.create(costCenter), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody CostCenter costCenter) {
        service.update(costCenter);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
