package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.entity.Message;
import com.example.repository.MessageRepository;

@Service
@Transactional
public class MessageService {

    MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository){
        this.messageRepository = messageRepository;
    }

    public Message postMessage(Message message){
        return messageRepository.save(message);
    }

    public List<Message> getAllMessages(){
        return messageRepository.findAll();
    }

    public Message getMessageById(long message_id){
        return messageRepository.getById(message_id);
    }

    public void deleteMessageById(long delete_id){
        messageRepository.deleteById(delete_id);
    }

    public Message patchMessage(String message_text, long patch_id){
        Message message = messageRepository.findById(patch_id).get();
        message.setMessage_text(message_text);
        messageRepository.save(message);
        return message;
    }

    public List<Message> getMessagesByAccount(int posted_by){
        return messageRepository.findMessagesByPostedBy(posted_by);
    }
}
