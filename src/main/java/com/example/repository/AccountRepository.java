package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

    
    @Query(value="SELECT * FROM account WHERE username = :usernameVar AND password = :passwordVar", nativeQuery = true)
    Account findAccountByUsernameAndPassword(@Param("usernameVar") String username, @Param("passwordVar") String password);

    @Query(value = "SELECT * FROM account WHERE username = :usernameVar", nativeQuery = true)
    Account findAccountByUsername(@Param("usernameVar") String username);
    
}
