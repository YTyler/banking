package com.ytyler.banking.entities;

import javax.persistence.*;

@Entity
@Table
public class Customer {
    @Id
    @SequenceGenerator(
            name = "item_sequence",
            sequenceName = "item_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "item_sequence"
    )
    private long customer_id;
    private long pan;
    private String citizen_uid;
    private String name;
    private String postal_address;
    private String email;

    public Customer() {
    }

    public Customer(long pan, String citizen_uid, String name, String postal_address, String email) {
        this.pan = pan;
        this.citizen_uid = citizen_uid;
        this.name = name;
        this.postal_address = postal_address;
        this.email = email;
    }

    public Customer(long customer_id, long pan, String citizen_uid, String name, String postal_address, String email) {
        this.customer_id = customer_id;
        this.pan = pan;
        this.citizen_uid = citizen_uid;
        this.name = name;
        this.postal_address = postal_address;
        this.email = email;
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

    @Override
    public String toString() {
        return "Customer{" +
                "customer_id=" + customer_id +
                ", pan=" + pan +
                ", citizen_uid='" + citizen_uid + '\'' +
                ", name='" + name + '\'' +
                ", postal_address='" + postal_address + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
