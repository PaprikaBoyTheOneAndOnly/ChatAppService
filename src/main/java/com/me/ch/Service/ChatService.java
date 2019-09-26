package com.me.ch.Service;

import com.me.ch.model.AccountManager;
import com.me.ch.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ChatService {

    @Autowired
    private SimpMessagingTemplate template;

    @Autowired
    private AccountManager accountManager;

    public ChatService() {
    }

    @MessageMapping("/sendMessage")
    public void handleMessage(SimpMessageHeaderAccessor sha, Message message) {
        this.accountManager.addMessage(message);
        this.template.convertAndSendToUser(message.getTo(), "chat/receiveMessage", message);
        this.template.convertAndSendToUser(sha.getUser().getName(), "chat/receiveMessage", message);
    }
}
