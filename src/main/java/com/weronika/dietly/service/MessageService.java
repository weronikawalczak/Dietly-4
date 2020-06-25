package com.weronika.dietly.service;

import com.rabbitmq.client.DeliverCallback;
import com.weronika.dietly.client.ExternalClient;
import com.weronika.dietly.entity.Message;
import com.weronika.dietly.rabbitmq.RabbitMQConnector;
import com.weronika.dietly.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    private final MessageRepository messageRepository;
    private final ExternalClient externalClient;
    private final RabbitMQConnector rabbitMQConnector;

    DeliverCallback deliverCallback = (consumerTag, delivery) -> {
        String message = new String(delivery.getBody(), "UTF-8");
        addMessage(new Message("Title", message));
    };

    public MessageService(MessageRepository messageRepository, @Qualifier("MailChimpClient") ExternalClient externalClient, RabbitMQConnector rabbitMQConnector) {
        this.messageRepository = messageRepository;
        this.externalClient = externalClient;
        this.rabbitMQConnector = rabbitMQConnector;
        this.rabbitMQConnector.setDeliverCallback(deliverCallback);
    }

    public Message addMessage(Message message){
        messageRepository.save(message);
        externalClient.sendMessage(message);
        return message;
    }


}
