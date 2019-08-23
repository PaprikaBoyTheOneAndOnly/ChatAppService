package com.me.ch.Service;

import com.me.ch.Model.Account;
import com.me.ch.Model.AccountManager;
import com.me.ch.Model.Message;
import com.me.ch.Model.RequestFailed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import javax.inject.Inject;

import static com.me.ch.Model.RequestFailed.RequestCode.FORBIDDEN;
import static com.me.ch.Model.RequestFailed.RequestCode.NOT_FOUND;

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
        try {
            this.manager.addMessageToAccounts(message);
            this.template.convertAndSendToUser(message.getTo(), "/chat/receiveMessage", message);
            this.template.convertAndSendToUser(sha.getUser().getName(), "/chat/receiveMessage", message);
        } catch (NullPointerException e) {
            this.template.convertAndSendToUser(sha.getUser().getName(),
                    "/chat/receiveMessage",
                    new RequestFailed(FORBIDDEN, "User " + message.getTo() + " does not exist!"));
        }

    }

    @MessageMapping("/getMessages")
    public void sendMessages(SimpMessageHeaderAccessor sha, Account account) {
        this.template.convertAndSendToUser(sha.getUser().getName(),
                "/chat/receiveChats",
                this.manager.getChatsFromAccount(account));
    }

    @MessageMapping("/isExistingAccount")
    public void isExistingAccount(SimpMessageHeaderAccessor sha, String username) {
        if (this.manager.isExistingAccount(username)) {
            this.template.convertAndSendToUser(sha.getUser().getName(), "chat/isExistingAccount", username);
        } else {
            this.template.convertAndSendToUser(sha.getUser().getName(), "chat/isExistingAccount",
                    new RequestFailed(NOT_FOUND, "Could not find \"" + username+"\""));
        }
    }
}
