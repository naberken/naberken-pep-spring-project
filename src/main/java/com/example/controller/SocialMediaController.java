package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.service.AccountService;
import com.example.service.MessageService;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@RestController
public class SocialMediaController {
    
    @Autowired
    AccountService accountService;
    
    @Autowired
    MessageService messageService;

    @PostMapping("register")
    public ResponseEntity<Account> registerAccount(@RequestBody Account account){

        Account response = accountService.registerAccount(account);
        System.out.println(response.toString());
        if(response == null){
            return ResponseEntity.status(400).body(null);
        }else if (response.getUsername() == "DUPLICATE"){
            return ResponseEntity.status(409).body(null);
        }

        return ResponseEntity.status(200).body(response);
    }

    @PostMapping("login")
    public ResponseEntity<Account> loginAccount(@RequestBody Account account){

        Account response = accountService.loginAccount(account);

        if(response == null){
            return ResponseEntity.status(401).body(null);
        }

        return ResponseEntity.status(200).body(response);
    }

    @PostMapping("messages")
    public ResponseEntity<Message> postMessage(@RequestBody Message message){

        Message response = messageService.postMessage(message);

        if(response == null){
            return ResponseEntity.status(400).body(null);
        }

        return ResponseEntity.status(200).body(response);
    }

    @GetMapping("messages")
    public ResponseEntity<List<Message>> getAllMessages(){

        List<Message> response = messageService.getAllMessages();

        return ResponseEntity.status(200).body(response);
    }

    @GetMapping("messages/{message_id}")
    public ResponseEntity<Message> getMessageById(@PathVariable("message_id") Integer messageId){
        
        return ResponseEntity.status(200).body(messageService.getMessageById(messageId));
    }

    @DeleteMapping("messages/{message_id}")
    public ResponseEntity<Integer> deleteMessageById(@PathVariable("message_id") Integer messageId){
        Integer response = messageService.deleteMessageById(messageId);
        return ResponseEntity.status(200).body(response);
    }

    @PatchMapping("messages/{message_id}")
    public ResponseEntity<Integer> patchMessage(@PathVariable("message_id") Integer messageId, @RequestBody Message message){
        Message response = messageService.patchMessage(message.getMessage_text(), messageId);
        
        if(response == null){
            return ResponseEntity.status(400).body(null);
        }
        
        return ResponseEntity.status(200).body(1);
    }

    @GetMapping("accounts/{account_id}/messages")
    public ResponseEntity<List<Message>> getMessagesByAccountId(@PathVariable("account_id") int accountId){
        return ResponseEntity.status(200).body(messageService.getMessagesByAccount(accountId));
    }
}
