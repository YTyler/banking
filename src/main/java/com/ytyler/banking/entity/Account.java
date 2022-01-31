package com.ytyler.banking.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class Account {
    @Id
    @SequenceGenerator(
            name = "account_sequence",
            sequenceName = "account_sequence",
            initialValue = 1000000000,
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "account_sequence"
    )
    private long account_number;
    private long balance;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="customer_id", nullable = false)
    Customer customer;

    @OneToMany(mappedBy = "account")
    List<Transaction> transactions;

    public Account() {
    }

    public Account(long account_number, long balance, Customer customer, List<Transaction> transactions) {
        this.account_number = account_number;
        this.balance = balance;
        this.customer = customer;
        this.transactions = transactions;
    }

    public long getAccount_number() {
        return account_number;
    }

    public void setAccount_number(long account_number) {
        this.account_number = account_number;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    @Override
    public String toString() {
        return "Account{" +
                "account_number=" + account_number +
                ", balance=" + balance +
                ", customer=" + customer +
                ", transactions=" + transactions +
                '}';
    }
}
