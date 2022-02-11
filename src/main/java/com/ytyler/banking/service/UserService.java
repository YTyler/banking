package com.ytyler.banking.service;

import com.ytyler.banking.entity.User;
import com.ytyler.banking.exception.InvalidLoginException;
import com.ytyler.banking.exception.ResourceNotFoundException;
import com.ytyler.banking.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepo userRepo;

    @Autowired
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    //get all Users
    public List<User> readAll() {
        return userRepo.findAll();
    }

    //get a User by id
    public User readById(Long id) throws ResourceNotFoundException {
        Optional<User> userOptional = userRepo.findById(id);
        if(userOptional.isEmpty()) {
            throw new ResourceNotFoundException("User with id: " + id + " was not found");
        } else {
            return userOptional.get();
        }
    }

    //create a new User
    public User create(User user) {
        return userRepo.save(user);
    }

    //update an existing User
    @Transactional
    public User update(long id, User user) throws ResourceNotFoundException {
        Optional<User> userOptional = userRepo.findById(id);
        if (userOptional.isEmpty()) {
            throw new ResourceNotFoundException("User with id: " + id + " was not found");
        } else {
            User userEdit = userOptional.get();
            //if a particular value is given replace it, else keep original value
            userEdit.setPassword(user.getPassword().length() > 0 ? user.getPassword() : userEdit.getPassword());
            userRepo.save(userEdit); //save and return the edited user
            return userEdit;
        }
    }

    //delete a User
    public User delete(Long id) throws ResourceNotFoundException{
        Optional<User> userOptional = userRepo.findById(id);
        if (userOptional.isEmpty()) {
            throw new ResourceNotFoundException("User with id: " + id + " was not found");
        }
        User user = userOptional.get();
        userRepo.deleteById(id);
        return user;
    }

    //find User by username and password
    public User login(User user) throws InvalidLoginException {
        Optional<User> userOptional = userRepo.loginUser(user.getUsername(), user.getPassword());
        if (userOptional.isEmpty()) {
            throw new InvalidLoginException("username and password combination do not match any users");
        } else {
            return userOptional.get();
        }
    }
}
