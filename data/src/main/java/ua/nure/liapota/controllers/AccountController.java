package ua.nure.liapota.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.nure.liapota.models.data.Account;
import ua.nure.liapota.services.AccountService;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    private final AccountService service;

    @Autowired
    public AccountController(AccountService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Account>> getAll() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Account> create(@RequestBody Account account) {
        return new ResponseEntity<>(service.create(account), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody Account account) {
        service.update(account);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
