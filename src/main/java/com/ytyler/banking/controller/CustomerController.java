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
    public ResponseEntity<Object> getById(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(customerService.readById(id), HttpStatus.OK);
        } catch(ResourceNotFoundException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping
    public ResponseEntity<Customer> postCustomer(@RequestBody Customer customer) {
        return new ResponseEntity<>(customerService.createCustomer(customer), HttpStatus.OK);
    }
    @PutMapping(path="{id}")
    public ResponseEntity<Object> putCustomer(@PathVariable Long id, @RequestBody Customer customer) {
        try{
            return new ResponseEntity<>(customerService.updateCustomer(id, customer), HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping(path="{id}")
    public ResponseEntity<Object> deleteCustomer(@PathVariable long id) {
        try {
            return new ResponseEntity<>(customerService.deleteCustomer(id), HttpStatus.OK);
        } catch(ResourceNotFoundException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
        }
    }
}
