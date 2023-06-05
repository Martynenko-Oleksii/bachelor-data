package ua.nure.liapota.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RestController;
import ua.nure.liapota.services.GlRpMappingService;
import ua.nure.liapota.services.ValueTypeService;

@RestController("/glRpMapping")
public class GlRpMappingController {
    private final GlRpMappingService service;
    private final ValueTypeService valueTypeService;

    @Autowired
    public GlRpMappingController(@Qualifier("glRpMappingService") GlRpMappingService service,
                                 ValueTypeService valueTypeService) {
        this.service = service;
        this.valueTypeService = valueTypeService;
    }
}
