package com.ytyler.banking.controller;

import com.ytyler.banking.entity.Transaction;
import com.ytyler.banking.exception.ResourceNotFoundException;
import com.ytyler.banking.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
@CrossOrigin
public class TransactionController {

    TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public ResponseEntity<List<Transaction>> getAll() {
        return new ResponseEntity<>(transactionService.readAll(), HttpStatus.OK);
    }

    @GetMapping(path="{id}")
    public ResponseEntity<Object> getById(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(transactionService.readById(id), HttpStatus.OK);
        } catch(ResourceNotFoundException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Object> postTransaction(@RequestBody Transaction transaction) {
        try {
            return new ResponseEntity<>(transactionService.create(transaction), HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(path="{id}")
    public ResponseEntity<Object> putTransaction(@PathVariable Long id, @RequestBody Transaction transaction) {
        try{
            return new ResponseEntity<>(transactionService.update(id, transaction), HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path="{id}")
    public ResponseEntity<Object> deleteTransaction(@PathVariable long id) {
        try {
            return new ResponseEntity<>(transactionService.delete(id), HttpStatus.OK);
        } catch(ResourceNotFoundException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
        }
    }

}
