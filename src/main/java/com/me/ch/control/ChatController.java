package com.me.ch.control;

import com.me.ch.model.Chat;
import com.me.ch.model.Message;
import com.me.ch.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

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

    @GetMapping("/loadChats")
    public ArrayList<Chat> loadChats(@RequestParam("username") String username) {
        return this.messageService.getChatsFromAccount(username);
    }

}
