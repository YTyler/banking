package com.ytyler.banking.service;


import com.ytyler.banking.entity.Customer;
import com.ytyler.banking.entity.Transaction;
import com.ytyler.banking.exception.ResourceNotFoundException;
import com.ytyler.banking.repository.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    private final TransactionRepo transactionRepo;

    @Autowired
    public TransactionService(TransactionRepo transactionRepo) {
        this.transactionRepo = transactionRepo;
    }

    //Date Validation Helper
    public static boolean isValidDate(String dateStr) {
        DateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
        sdf.setLenient(false);
        try {
            sdf.parse(dateStr);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    //Service Functions
    //get all Transactions
    public List<Transaction> readAll() {
        return transactionRepo.findAll();
    }

    //get a Transaction by id
    public Transaction readById(Long id) throws ResourceNotFoundException {
        Optional<Transaction> transactionOptional = transactionRepo.findById(id);
        if(transactionOptional.isEmpty()) {
            throw new ResourceNotFoundException("Transaction with id: " + id + " was not found");
        } else {
            return transactionOptional.get();
        }
    }

    //create a new Transaction
    public Transaction create(Transaction transaction) {
        return transactionRepo.save(transaction);
    }

    //update an existing Transaction
    @Transactional
    public Transaction update(long id, Transaction transaction) throws ResourceNotFoundException {
        Optional<Transaction> transactionOptional = transactionRepo.findById(id);
        if (transactionOptional.isEmpty()) {
            throw new ResourceNotFoundException("Transaction with id: " + id + " was not found");
        } else {
            Transaction transactionEdit = transactionOptional.get();
            //if a particular value is given replace it, else keep original value
            transactionEdit.setReference_number(transaction.getReference_number() > 0 ? transaction.getReference_number() : transactionEdit.getReference_number());
            transactionEdit.setDate_time(isValidDate(transaction.getDate_time()) ? transaction.getDate_time() : transactionEdit.getDate_time());
            transactionEdit.setType(transaction.getType().length() > 0 ? transaction.getType() : transactionEdit.getType());
            transactionEdit.setSubtype(transaction.getSubtype().length() > 0 ? transaction.getSubtype() : transactionEdit.getSubtype());
            transactionEdit.setAmount(transaction.getAmount() > 0 ? transaction.getAmount() : transactionEdit.getAmount());
            transactionRepo.save(transactionEdit); //save and return the edited transaction
            return transactionEdit;
        }
    }

    //delete a Transaction
    public Transaction delete(Long id) throws ResourceNotFoundException{
        Optional<Transaction> transactionOptional = transactionRepo.findById(id);
        if (transactionOptional.isEmpty()) {
            throw new ResourceNotFoundException("Transaction with id: " + id + " was not found");
        }
        Transaction transaction = transactionOptional.get();
        transactionRepo.deleteById(id);
        return transaction;
    }
}
