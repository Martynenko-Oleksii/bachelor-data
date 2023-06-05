package ua.nure.liapota.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.nure.liapota.annotations.Authorize;
import ua.nure.liapota.models.data.TimePeriodFacility;
import ua.nure.liapota.models.security.Facility;
import ua.nure.liapota.models.security.UserCustomer;
import ua.nure.liapota.services.TimePeriodFacilityService;
import ua.nure.liapota.services.TimePeriodService;
import ua.nure.liapota.services.UserCustomerService;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

@Authorize("data,data-configuration")
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

    @GetMapping("/currentTimePeriod/{id}")
    public ResponseEntity<TimePeriodFacility> getCurrentTimePeriod(@PathVariable Integer id) {
        TimePeriodFacility timePeriodFacility = timePeriodFacilityService.getTimePeriodByFacilityId(id);

        if (timePeriodFacility == null) {
            timePeriodFacility = new TimePeriodFacility();
            timePeriodFacility.setTimePeriod(timePeriodService.getCurrent());
            timePeriodFacility.setFacilityId(id);
            timePeriodFacility.setStatus("Opened");
        }

        return new ResponseEntity<>(timePeriodFacility, HttpStatus.OK);
    }
}
