package com.ytyler.banking.repository;

import com.ytyler.banking.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long> {
    @Query(value = "SELECT * FROM CUSTOMER c WHERE c.pan = ?1", nativeQuery = true)
    Optional<Customer> findByPan(long pan);
}
