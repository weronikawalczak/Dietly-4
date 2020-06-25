package com.weronika.dietly.controller;

import com.weronika.dietly.entity.Message;
import com.weronika.dietly.service.MessageService;
import org.springframework.web.bind.annotation.*;


@RestController
public class MessageController {
    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/message")
    public Message addMessage(@RequestBody Message message){
        return messageService.addMessage(message);
    }
}
