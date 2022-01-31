package com.ytyler.banking.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class Customer {
    @Id
    @SequenceGenerator(
            name = "customer_sequence",
            sequenceName = "customer_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "customer_sequence"
    )
    private long customer_id;
    private long pan;
    private String citizen_uid;
    private String name;
    private String postal_address;
    private String email;

    @OneToOne(mappedBy = "customer")
    User user;

    @OneToMany(mappedBy = "customer")
    List<Account> accounts;

    public Customer() {
    }

    public Customer(long customer_id, long pan, String citizen_uid, String name, String postal_address, String email, User user, List<Account> accounts) {
        this.customer_id = customer_id;
        this.pan = pan;
        this.citizen_uid = citizen_uid;
        this.name = name;
        this.postal_address = postal_address;
        this.email = email;
        this.user = user;
        this.accounts = accounts;
    }

    public long getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(long customer_id) {
        this.customer_id = customer_id;
    }

    public long getPan() {
        return pan;
    }

    public void setPan(long pan) {
        this.pan = pan;
    }

    public String getCitizen_uid() {
        return citizen_uid;
    }

    public void setCitizen_uid(String citizen_uid) {
        this.citizen_uid = citizen_uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPostal_address() {
        return postal_address;
    }

    public void setPostal_address(String postal_address) {
        this.postal_address = postal_address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customer_id=" + customer_id +
                ", pan=" + pan +
                ", citizen_uid='" + citizen_uid + '\'' +
                ", name='" + name + '\'' +
                ", postal_address='" + postal_address + '\'' +
                ", email='" + email + '\'' +
                ", user=" + user +
                ", accounts=" + accounts +
                '}';
    }
}
