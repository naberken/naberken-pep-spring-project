package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.repository.MessageRepository;

@Service
@Transactional
public class MessageService {

    private MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository){
        this.messageRepository = messageRepository;
    }

    public Message postMessage(Message message){

        if(message.getMessage_text().length() > 255){
            return null;
        } else if (message.getMessage_text().length() <= 0){
            return null;
        }
        try{
            messageRepository.findAccountByPostedBy(message.getPosted_by());
        } catch(Exception e){
            return messageRepository.save(message);
        }      
        return null;  
    }

    public List<Message> getAllMessages(){
        return messageRepository.findAll();
    }

    public Message getMessageById(Integer messageId){

        if(messageRepository.findById(messageId).isPresent()){
            return messageRepository.getById(messageId);
        }
        return null;
    }

    public Integer deleteMessageById(Integer deleteId){
        if(messageRepository.findById(deleteId).isPresent()){
            messageRepository.deleteById(deleteId);
            return 1;
        } else {
            return null;
        }       
    }

    public Message patchMessage(String messageText, Integer patchId){
        if(messageRepository.findById(patchId).isPresent() && messageText.length() > 0 && messageText.length() < 255){      
            Message message = messageRepository.getById(patchId);        
            message.setMessage_text(messageText);           
            return messageRepository.save(message);     
        }
        return null;
    }

    public List<Message> getMessagesByAccount(int postedBy){
        return messageRepository.findMessagesByPostedBy(postedBy);
    }
}
