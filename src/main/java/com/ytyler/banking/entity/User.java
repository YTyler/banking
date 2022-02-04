package com.ytyler.banking.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ytyler.banking.generator.PasswordGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table
public class User {

    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    private long user_id;
    @Column(unique = true)
    private String username;
    private String password;
    private String role;
    private Boolean verified;

    @JsonManagedReference
    @OneToOne(mappedBy = "user")
    private Customer customer;

    @PrePersist
    public void initialPassword() {
        if(password == null) {
            password = PasswordGenerator.generateRandomPassword();
        }
        if(verified == null) {
            verified = false;
        }
    }
}
