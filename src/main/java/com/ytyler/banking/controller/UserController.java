package com.ytyler.banking.controller;

import com.ytyler.banking.entity.Customer;
import com.ytyler.banking.entity.User;
import com.ytyler.banking.exception.InvalidLoginException;
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
    public ResponseEntity<User> getById(@PathVariable("id") Long id) throws ResourceNotFoundException {
            return new ResponseEntity<>(userService.readById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> postUser(@RequestBody User user) {
        return new ResponseEntity<>(userService.create(user), HttpStatus.OK);
    }

    @PostMapping(path="/login")
    public ResponseEntity<User> loginUser(@RequestBody User user) throws InvalidLoginException {
        return new ResponseEntity<>(userService.login(user), HttpStatus.OK);
    }

    @PutMapping(path="{id}")
    public ResponseEntity<User> putUser(@PathVariable Long id, @RequestBody User user) throws ResourceNotFoundException {
            return new ResponseEntity<>(userService.update(id, user), HttpStatus.OK);
    }

    @DeleteMapping(path="{id}")
    public ResponseEntity<User> deleteUser(@PathVariable long id) throws ResourceNotFoundException {
            return new ResponseEntity<>(userService.delete(id), HttpStatus.OK);
    }
}
