package ua.nure.liapota.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.nure.liapota.annotations.Authorize;
import ua.nure.liapota.models.data.Account;
import ua.nure.liapota.services.AccountService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Authorize("data,data-configuration")
@RestController
@RequestMapping("/accounts")
@CrossOrigin(origins = "http://localhost:4200")
public class AccountController {
    private final AccountService service;

    @Autowired
    public AccountController(AccountService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Account>> getAll(@PathVariable Integer id) {
        return new ResponseEntity<>(service.getByFacilityId(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Account> create(@RequestBody Account account,
                                          HttpServletRequest request) {
        account.setAddedBy((String) request.getAttribute("userId"));
        return new ResponseEntity<>(service.create(account), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody Account account,
                                       HttpServletRequest request) {
        account.setAddedBy((String) request.getAttribute("userId"));
        service.update(account);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
