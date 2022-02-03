package com.ytyler.banking.controller;

import com.ytyler.banking.entity.Account;
import com.ytyler.banking.exception.ResourceNotFoundException;
import com.ytyler.banking.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
@CrossOrigin
public class AccountController {

    AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public ResponseEntity<List<Account>> getAll() {
        return new ResponseEntity<>(accountService.readAll(), HttpStatus.OK);
    }

    @GetMapping(path="{account_number}")
    public ResponseEntity<Object> getById(@PathVariable("account_number") Long account_number) throws ResourceNotFoundException {
        return new ResponseEntity<>(accountService.readById(account_number), HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<Object> postAccount(@RequestBody Account account) throws ResourceNotFoundException {
        return new ResponseEntity<>(accountService.create(account), HttpStatus.OK);
    }

    @PutMapping(path="{account_number}")
    public ResponseEntity<Object> putAccount(@PathVariable Long account_number, @RequestBody Account account) throws ResourceNotFoundException {
        return new ResponseEntity<>(accountService.update(account_number, account), HttpStatus.OK);
    }

    @DeleteMapping(path="{account_number}")
    public ResponseEntity<Object> deleteAccount(@PathVariable long account_number) throws ResourceNotFoundException {
        return new ResponseEntity<>(accountService.delete(account_number), HttpStatus.OK);
    }
}
