package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entity.Account;
import com.example.entity.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {
    
    @Query(value = "SELECT * FROM message WHERE posted_by = :postedByVar", nativeQuery = true)
    List<Message> findMessagesByPostedBy(@Param("postedByVar") int postedBy);
    
    @Query(value = "SELECT * FROM account WHERE account_id = :postedByVar", nativeQuery = true)
    Account findAccountByPostedBy(@Param("postedByVar") int postedBy);
}
