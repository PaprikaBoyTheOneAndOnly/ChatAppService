package com.me.ch.Service;

import com.me.ch.Model.AccountManager;
import com.me.ch.Model.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private Logger logger = LoggerFactory.getLogger(ChatService.class);

    public ChatService() {
    }

    @MessageMapping("/sendMessage")
    public void accountManager(SimpMessageHeaderAccessor sha, Message message) {
        this.logger.info(message.toString());
        this.accountManager.addMessageToAccounts(message);
        this.template.convertAndSendToUser(message.getTo(), "chat/receiveMessage", message);
        this.template.convertAndSendToUser(sha.getUser().getName(), "chat/receiveMessage", message);
    }
}
