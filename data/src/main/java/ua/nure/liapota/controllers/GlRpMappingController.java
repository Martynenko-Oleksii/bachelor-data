package ua.nure.liapota.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.nure.liapota.models.data.Account;
import ua.nure.liapota.models.data.GlRpMapping;
import ua.nure.liapota.models.data.ValueTypeEntity;
import ua.nure.liapota.services.AccountService;
import ua.nure.liapota.services.GlRpMappingService;
import ua.nure.liapota.services.ValueTypeService;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/glPrMapping")

public class GlRpMappingController {
    private final GlRpMappingService service;
    private final ValueTypeService valueTypeService;
    private final AccountService accountService;

    @Autowired
    public GlRpMappingController(@Qualifier("glRpMappingService") GlRpMappingService service,
                                 ValueTypeService valueTypeService,
                                 AccountService accountService) {
        this.service = service;
        this.valueTypeService = valueTypeService;
        this.accountService = accountService;
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
}
