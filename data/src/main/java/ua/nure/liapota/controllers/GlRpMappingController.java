package ua.nure.liapota.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.nure.liapota.annotations.Authorize;
import ua.nure.liapota.models.data.ValueTypeEntity;
import ua.nure.liapota.services.GlRpMappingService;
import ua.nure.liapota.services.ValueTypeService;

import java.util.List;

@Authorize("data,gl-pr-mapping")
@RestController
@RequestMapping("/glPrMapping")
@CrossOrigin(origins = "http://localhost:4200")
public class GlRpMappingController {
    private final GlRpMappingService service;
    private final ValueTypeService valueTypeService;

    @Autowired
    public GlRpMappingController(@Qualifier("glRpMappingService") GlRpMappingService service,
                                 ValueTypeService valueTypeService) {
        this.service = service;
        this.valueTypeService = valueTypeService;
    }

    @GetMapping("/accountTypes")
    public ResponseEntity<List<ValueTypeEntity>> getAccountTypes() {
        return new ResponseEntity<>(valueTypeService.getAll(), HttpStatus.OK);
    }
}
