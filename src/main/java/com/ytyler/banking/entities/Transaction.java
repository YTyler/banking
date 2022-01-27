package com.ytyler.banking.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
public class Transaction {
    @Id
    @SequenceGenerator(
            name = "transaction_sequence",
            sequenceName = "transaction_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "transaction_sequence"
    )
    private long transaction_id;
    private long reference_number;
    private Date date_time;
    private String type;
    private String subtype;
    private long amount;

    public Transaction() {
    }

    public Transaction(long reference_number, Date date_time, String type, String subtype, long amount) {
        this.reference_number = reference_number;
        this.date_time = date_time;
        this.type = type;
        this.subtype = subtype;
        this.amount = amount;
    }

    public Transaction(long transaction_id, long reference_number, Date date_time, String type, String subtype, long amount) {
        this.transaction_id = transaction_id;
        this.reference_number = reference_number;
        this.date_time = date_time;
        this.type = type;
        this.subtype = subtype;
        this.amount = amount;
    }

    public long getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(long transaction_id) {
        this.transaction_id = transaction_id;
    }

    public long getReference_number() {
        return reference_number;
    }

    public void setReference_number(long reference_number) {
        this.reference_number = reference_number;
    }

    public Date getDate_time() {
        return date_time;
    }

    public void setDate_time(Date date_time) {
        this.date_time = date_time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transaction_id=" + transaction_id +
                ", reference_number=" + reference_number +
                ", date_time=" + date_time +
                ", type='" + type + '\'' +
                ", subtype='" + subtype + '\'' +
                ", amount=" + amount +
                '}';
    }
}
