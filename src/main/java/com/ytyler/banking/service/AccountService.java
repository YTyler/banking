package com.ytyler.banking.service;

import com.ytyler.banking.entity.Account;
import com.ytyler.banking.entity.Customer;
import com.ytyler.banking.exception.ResourceNotFoundException;
import com.ytyler.banking.repository.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    private final AccountRepo accountRepo;
    private final CustomerService customerService;

    @Autowired
    public AccountService(AccountRepo accountRepo, CustomerService customerService) {
        this.accountRepo = accountRepo;
        this.customerService = customerService;
    }

    //get all accounts
    public List<Account> readAll() {
        return accountRepo.findAll();
    }

    //get an Account by id
    public Account readById(Long account_number) throws ResourceNotFoundException {
        Optional<Account> accountOptional = accountRepo.findById(account_number);
        if(accountOptional.isEmpty()) {
            throw new ResourceNotFoundException("Account: " + account_number + " was not found");
        } else {
            return accountOptional.get();
        }
    }

    //create a new Account
    public Account create(Account account) throws  ResourceNotFoundException{
        Customer customer = customerService.readById(account.getCustomer().getCustomer_id());
        account.setCustomer(customer);
        return accountRepo.save(account);
    }

    //update an existing Account
    @Transactional
    public Account update(long account_number, Account account) throws ResourceNotFoundException {
        Optional<Account> accountOptional = accountRepo.findById(account_number);
        if (accountOptional.isEmpty()) {
            throw new ResourceNotFoundException("Account: " + account_number + " was not found");
        } else {
            Account accountEdit = accountOptional.get();
            //if a particular value is given replace it, else keep original value
            accountEdit.setBalance(account.getBalance() >= 0 ? account.getBalance() : accountEdit.getBalance());
            accountRepo.save(accountEdit); //save and return the edited account
            return accountEdit;
        }
    }
    //delete an Account
    public Account delete(Long account_number) throws ResourceNotFoundException{
        Optional<Account> accountOptional = accountRepo.findById(account_number);
        if (accountOptional.isEmpty()) {
            throw new ResourceNotFoundException("Account: " + account_number + " was not found");
        }
        Account account = accountOptional.get();
        accountRepo.deleteById(account_number);
        return account;
    }
}
