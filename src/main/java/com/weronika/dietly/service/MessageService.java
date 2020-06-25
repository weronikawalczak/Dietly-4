package com.weronika.dietly.service;

import com.weronika.dietly.client.ExternalClient;
import com.weronika.dietly.entity.Message;
import com.weronika.dietly.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    private final MessageRepository messageRepository;
    private final ExternalClient externalClient;

    public MessageService(MessageRepository messageRepository, @Qualifier("MailChimpClient") ExternalClient externalClient) {
        this.messageRepository = messageRepository;
        this.externalClient = externalClient;
    }

    public Message addMessage(Message message){
        messageRepository.save(message);
        externalClient.sendMessage(message);
        return message;
    }
}
