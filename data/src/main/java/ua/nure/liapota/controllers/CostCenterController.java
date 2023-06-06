package ua.nure.liapota.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.nure.liapota.annotations.Authorize;
import ua.nure.liapota.models.data.CostCenter;
import ua.nure.liapota.services.CostCenterService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Authorize("data,data-configuration")
@RestController
@RequestMapping("/costCenters")
@CrossOrigin(origins = "http://localhost:4200")
public class CostCenterController {
    CostCenterService service;

    public CostCenterController(CostCenterService service){
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<CostCenter>> getAll(@PathVariable Integer id) {
        return new ResponseEntity<>(service.getByFacility(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CostCenter> create(@RequestBody CostCenter costCenter,
                                             HttpServletRequest request) {
        costCenter.setAddedBy((String) request.getAttribute("userId"));
        return new ResponseEntity<>(service.create(costCenter), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody CostCenter costCenter,
                                       HttpServletRequest request) {
        costCenter.setAddedBy((String) request.getAttribute("userId"));
        service.update(costCenter);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
