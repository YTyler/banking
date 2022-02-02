package com.ytyler.banking.controller;

import com.ytyler.banking.entity.Customer;
import com.ytyler.banking.entity.User;
import com.ytyler.banking.exception.ResourceNotFoundException;
import com.ytyler.banking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {

    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAll() {
        return new ResponseEntity<>(userService.readAll(), HttpStatus.OK);
    }

    @GetMapping(path="{id}")
    public ResponseEntity<Object> getById(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(userService.readById(id), HttpStatus.OK);
        } catch(ResourceNotFoundException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<User> postUser(@RequestBody User user) {
        return new ResponseEntity<>(userService.create(user), HttpStatus.OK);
    }

    @PutMapping(path="{id}")
    public ResponseEntity<Object> putUser(@PathVariable Long id, @RequestBody User user) {
        try{
            return new ResponseEntity<>(userService.update(id, user), HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path="{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable long id) {
        try {
            return new ResponseEntity<>(userService.delete(id), HttpStatus.OK);
        } catch(ResourceNotFoundException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
        }
    }
}
