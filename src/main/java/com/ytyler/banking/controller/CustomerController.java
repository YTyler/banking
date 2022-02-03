package com.ytyler.banking.controller;

import com.ytyler.banking.entity.Customer;
import com.ytyler.banking.exception.ResourceNotFoundException;
import com.ytyler.banking.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
@CrossOrigin
public class CustomerController {

    CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAll() {
        return new ResponseEntity<>(customerService.readAll(), HttpStatus.OK);
    }

    @GetMapping(path="{id}")
    public ResponseEntity<Object> getById(@PathVariable("id") Long id) throws ResourceNotFoundException {
        return new ResponseEntity<>(customerService.readById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> postCustomer(@RequestBody Customer customer) throws ResourceNotFoundException {
        return new ResponseEntity<>(customerService.create(customer), HttpStatus.OK);
    }

    @PutMapping(path="{id}")
    public ResponseEntity<Object> putCustomer(@PathVariable Long id, @RequestBody Customer customer) throws ResourceNotFoundException {
        return new ResponseEntity<>(customerService.update(id, customer), HttpStatus.OK);
    }

    @DeleteMapping(path="{id}")
    public ResponseEntity<Object> deleteCustomer(@PathVariable long id) throws ResourceNotFoundException {
        return new ResponseEntity<>(customerService.delete(id), HttpStatus.OK);
    }
}
