package com.me.ch.Service;

import com.me.ch.Model.AccountManager;
import com.me.ch.Model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import javax.inject.Inject;

@Controller
public class ChatService {

    @Autowired
    private SimpMessagingTemplate template;

    @Inject
    private AccountManager manager;

    public ChatService() {
    }

    @MessageMapping("/sendMessage")
    public void receiveMessage(SimpMessageHeaderAccessor sha, Message message) {
        this.manager.addMessageToAccounts(message);
        this.template.convertAndSendToUser(message.getTo(), "chat/receiveMessage", message);
        this.template.convertAndSendToUser(sha.getUser().getName(), "chat/receiveMessage", message);
    }
}
