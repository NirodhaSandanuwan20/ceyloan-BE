package com.exam.controller;

import com.exam.model.Message;
import com.exam.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/message")
@CrossOrigin("*")
public class MessageController {
    private MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/")
    public Message submit(@RequestBody Message message){
        System.out.println(message);
       return this.messageService.saveMessage(message);
    }
}
