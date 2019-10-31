package com.me.ch.control;

import com.me.ch.model.Message;
import com.me.ch.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    @Autowired
    private SimpMessagingTemplate template;

    @Autowired
    private MessageService messageService;

    public ChatController() {
    }

    @MessageMapping("/sendMessage")
    public void handleMessage(SimpMessageHeaderAccessor sha, Message message) {
        this.messageService.addMessage(message);
        this.template.convertAndSendToUser(message.getTo(), "chat/receiveMessage", message);
        this.template.convertAndSendToUser(sha.getUser().getName(), "chat/receiveMessage", message);
    }
}
