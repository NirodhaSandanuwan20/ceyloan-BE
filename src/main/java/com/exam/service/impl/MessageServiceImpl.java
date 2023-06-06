package com.exam.service.impl;

import com.exam.model.Message;
import com.exam.repo.MessageRepo;
import com.exam.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {
    private MessageRepo messageRepo;

    @Autowired
    public MessageServiceImpl(MessageRepo messageRepo) {
        this.messageRepo = messageRepo;
    }

    @Override
    public Message saveMessage(Message msg) {
        System.out.println(msg);
        return this.messageRepo.save(msg);
    }
}
