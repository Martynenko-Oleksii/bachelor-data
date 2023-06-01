package ua.nure.liapota.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.nure.liapota.models.data.Department;
import ua.nure.liapota.models.util.Confirm;
import ua.nure.liapota.services.CostCenterService;
import ua.nure.liapota.services.DepartmentService;

import java.util.List;

@RestController
@RequestMapping("/CCMapping")
public class CostCenterMappingController {
    private final DepartmentService departmentService;
    private final CostCenterService costCenterService;

    @Autowired
    public CostCenterMappingController(DepartmentService departmentService, CostCenterService costCenterService) {
        this.departmentService = departmentService;
        this.costCenterService = costCenterService;
    }

    @GetMapping("/department/{id}")
    public ResponseEntity<List<Department>> getDepartmentsByFacilityId(@PathVariable Integer id) {
        return new ResponseEntity<>(departmentService.getByFacilityId(id), HttpStatus.OK);
    }

    @GetMapping("/confirm/{id}")
    public ResponseEntity<Confirm> confirm(@PathVariable Integer id) {
        return new ResponseEntity<>(costCenterService.getConfirm(id), HttpStatus.OK);
    }

    @PostMapping("/department")
    public ResponseEntity<Department> createDepartment(@RequestBody Department department) {
        return new ResponseEntity<>( departmentService.create(department), HttpStatus.OK);
    }

    @PutMapping("/department")
    public ResponseEntity<Void> updateDepartment(@RequestBody Department department) {
        departmentService.update(department);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/department/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable Integer id) {
        departmentService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
