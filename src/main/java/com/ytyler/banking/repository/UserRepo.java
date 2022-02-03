package com.ytyler.banking.repository;

import com.ytyler.banking.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    @Query(value = "SELECT * FROM USER u WHERE u.username = ?1 AND u.password = ?2", nativeQuery = true)
    Optional<User> loginUser(String username, String password);
}
