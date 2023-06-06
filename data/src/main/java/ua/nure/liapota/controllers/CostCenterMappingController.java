package ua.nure.liapota.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.nure.liapota.annotations.Authorize;
import ua.nure.liapota.models.data.CostCenter;
import ua.nure.liapota.models.data.Department;
import ua.nure.liapota.models.security.StandardDepartment;
import ua.nure.liapota.models.util.Confirm;
import ua.nure.liapota.services.CostCenterService;
import ua.nure.liapota.services.DepartmentService;
import ua.nure.liapota.services.StandardDepartmentService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Authorize("data,cc-mapping")
@RestController
@RequestMapping("/CCMapping")
@CrossOrigin(origins = "http://localhost:4200")
public class CostCenterMappingController {
    private final DepartmentService departmentService;
    private final CostCenterService costCenterService;
    private final StandardDepartmentService standardDepartmentService;

    @Autowired
    public CostCenterMappingController(DepartmentService departmentService,
                                       CostCenterService costCenterService,
                                       StandardDepartmentService standardDepartmentService) {
        this.departmentService = departmentService;
        this.costCenterService = costCenterService;
        this.standardDepartmentService = standardDepartmentService;
    }

    @GetMapping("/costCenter/{id}")
    public ResponseEntity<List<CostCenter>> getCostCenters(@PathVariable Integer id) {
        return new ResponseEntity<>(costCenterService.getByFacility(id), HttpStatus.OK);
    }

    @GetMapping("/standardDepartment/{id}")
    public ResponseEntity<List<StandardDepartment>> getStandardDepartments(@PathVariable Integer id,
                                                                           HttpServletRequest request) {
        return new ResponseEntity<>(standardDepartmentService.getStandardDepartments(
                (String) request.getAttribute("userId"), id), HttpStatus.OK);
    }

    @GetMapping("department")
    public ResponseEntity<List<Department>> getDepartments(
            @RequestParam(name = "facilityId") Integer facilityId,
            @RequestParam(name = "standardDepartmentId") Integer standardDepartmentId) {
        return new ResponseEntity<>(departmentService.getByFacilityId(facilityId, standardDepartmentId), HttpStatus.OK);
    }

    @PutMapping("/costCenter")
    public ResponseEntity<Void> updateMapping(@RequestBody CostCenter costCenter) {
        costCenterService.update(costCenter);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/costCenter/deleteMapping")
    public ResponseEntity<Void> deleteMapping(@RequestBody CostCenter costCenter) {
        costCenterService.update(costCenter);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/confirm/{id}")
    public ResponseEntity<Confirm> confirm(@PathVariable Integer id) {
        return new ResponseEntity<>(costCenterService.getConfirm(id), HttpStatus.OK);
    }

    @PostMapping("/department")
    public ResponseEntity<Department> createDepartment(@RequestBody Department department) {
        return new ResponseEntity<>( departmentService.create(department), HttpStatus.OK);
    }

    @DeleteMapping("/department/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable Integer id) {
        departmentService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
