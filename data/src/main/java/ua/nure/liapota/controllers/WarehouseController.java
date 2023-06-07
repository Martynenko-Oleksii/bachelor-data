package ua.nure.liapota.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.nure.liapota.annotations.Authorize;
import ua.nure.liapota.models.data.TimePeriodFacility;
import ua.nure.liapota.models.data.Value;
import ua.nure.liapota.models.warehouse.DepartmentInstanceWarehouse;
import ua.nure.liapota.models.warehouse.FacilityWarehouse;
import ua.nure.liapota.models.warehouse.Measure;
import ua.nure.liapota.models.warehouse.TimePeriodWarehouse;
import ua.nure.liapota.services.TimePeriodFacilityService;
import ua.nure.liapota.services.WarehouseService;

import java.util.List;

@Authorize("data,data-administration")
@RestController
@RequestMapping("/timePeriod")
@CrossOrigin(origins = "http://localhost:4200")
public class WarehouseController {
    private final TimePeriodFacilityService timePeriodFacilityService;
    private final WarehouseService warehouseService;

    @Autowired
    public WarehouseController(TimePeriodFacilityService timePeriodFacilityService,
                               WarehouseService warehouseService) {
        this.timePeriodFacilityService = timePeriodFacilityService;
        this.warehouseService = warehouseService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<TimePeriodFacility>> getTimePeriods(@PathVariable Integer id) {
        return new ResponseEntity<>(timePeriodFacilityService.getTimePeriodByFacilityId(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> closeTimePeriod(@PathVariable Integer id){
        TimePeriodFacility closing = timePeriodFacilityService.close(id);
        FacilityWarehouse facilityWarehouse = warehouseService.getFacilityWarehouse(closing);
        TimePeriodWarehouse timePeriodWarehouse = warehouseService.getTimePeriodWarehouse(closing);
        List<Value> values = warehouseService.getValuesByTimePeriod(closing);

        for (Value v : values) {
            DepartmentInstanceWarehouse departmentInstanceWarehouse = warehouseService.getDepartmentInstance(v);
            Measure measure = warehouseService.getMeasure(v);
            warehouseService.setValue(facilityWarehouse,
                    timePeriodWarehouse,
                    departmentInstanceWarehouse,
                    measure, v);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
