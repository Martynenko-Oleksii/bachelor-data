package ua.nure.liapota.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.nure.liapota.models.data.TimePeriodFacility;
import ua.nure.liapota.models.security.Facility;
import ua.nure.liapota.models.security.UserCustomer;
import ua.nure.liapota.services.TimePeriodFacilityService;
import ua.nure.liapota.services.TimePeriodService;
import ua.nure.liapota.services.UserCustomerService;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

@RestController
@RequestMapping("/configuration")
@CrossOrigin(origins = "http://localhost:4200")
public class ConfigurationController {
    private final UserCustomerService service;
    private final TimePeriodFacilityService timePeriodFacilityService;
    private final TimePeriodService timePeriodService;

    public ConfigurationController(UserCustomerService service,
                                   TimePeriodFacilityService timePeriodFacilityService,
                                   TimePeriodService timePeriodService) {
        this.service = service;
        this.timePeriodFacilityService = timePeriodFacilityService;
        this.timePeriodService = timePeriodService;
    }

    @GetMapping("/facilities")
    public ResponseEntity<Set<Facility>> getFacilities(HttpServletRequest request) {
        UserCustomer user = service.getById((String) request.getAttribute("userId"));
        return new ResponseEntity<>(user.getFacilityGroup().getFacilities(), HttpStatus.OK);
    }

    @GetMapping("/currentTimePeriod")
    public ResponseEntity<TimePeriodFacility> getCurrentTimePeriod(@RequestBody Facility facility) {
        TimePeriodFacility timePeriodFacility = timePeriodFacilityService.getTimePeriodByFacilityId(facility.getId());

        if (timePeriodFacility == null) {
            timePeriodFacility = new TimePeriodFacility();
            timePeriodFacility.setTimePeriod(timePeriodService.getCurrent());
            timePeriodFacility.setFacilityId(facility.getId());
            timePeriodFacility.setStatus("Opened");
        }

        return new ResponseEntity<>(timePeriodFacility, HttpStatus.OK);
    }
}
