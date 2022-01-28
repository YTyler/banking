package com.ytyler.banking.service;

import com.ytyler.banking.entity.Customer;
import com.ytyler.banking.exception.ResourceNotFoundException;
import com.ytyler.banking.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepo customerRepo;

    @Autowired
    public CustomerService(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    //get all Customers
    public List<Customer> readAll() {
        return customerRepo.findAll();
    }

    //get a Customer by id
    public Customer readById(Long id) throws ResourceNotFoundException {
        Optional<Customer> customerOptional = customerRepo.findById(id);
        if(customerOptional.isEmpty()) {
            throw new ResourceNotFoundException("Customer with id: " + id + " was not found");
        } else {
            return customerOptional.get();
        }
    }

    //create a new Customer
    public Customer createCustomer(Customer customer) {
        return customerRepo.save(customer);
    }

    //update an existing Customer
    @Transactional
    public Customer updateCustomer(long id, Customer customer) throws ResourceNotFoundException {
        Optional<Customer> customerOptional = customerRepo.findById(id);
        if (customerOptional.isEmpty()) {
            throw new ResourceNotFoundException("Customer with id: " + id + " was not found");
        } else {
            Customer customerEdit = customerOptional.get();
            //if a particular value is given replace it, else keep original value
            customerEdit.setPan(customer.getPan() > 0 ? customer.getPan() : customerEdit.getPan());
            customerEdit.setCitizen_uid(customer.getCitizen_uid().length() > 0 ? customer.getCitizen_uid() : customerEdit.getCitizen_uid());
            customerEdit.setName(customer.getName().length() > 0 ? customer.getName() : customerEdit.getName());
            customerEdit.setPostal_address(customer.getPostal_address().length() > 0 ? customer.getPostal_address() : customerEdit.getPostal_address());
            customerEdit.setEmail(customer.getEmail().length() > 0 ? customer.getEmail() : customerEdit.getEmail());
            customerRepo.save(customerEdit); //save and return the edited customer
            return customerEdit;
        }
    }

    //delete a Customer
    public Customer deleteCustomer(Long id) throws ResourceNotFoundException{
        Optional<Customer> customerOptional = customerRepo.findById(id);
        if (customerOptional.isEmpty()) {
            throw new ResourceNotFoundException("Customer with id: " + id + " was not found");
        }
        Customer customer = customerOptional.get();
        customerRepo.deleteById(id);
        return customer;
    }
}
