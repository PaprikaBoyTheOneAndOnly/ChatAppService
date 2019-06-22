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

@Controller
public class ChatService {
    @Autowired
    private SimpMessagingTemplate template;

    @Inject
    private AccountManager manager;

    public ChatService() {
    }


    @MessageMapping("/connect")
    public void connect(SimpMessageHeaderAccessor sha, Account account) {
        this.manager.setUUIDForAccount(account, sha.getUser().getName());
    }

    @MessageMapping("/sendMessage")
    public void receiveMessage(SimpMessageHeaderAccessor sha, Message message) {
        try {
            this.manager.addMessageToAccounts(message);
            this.template.convertAndSendToUser(this.manager.getUUIDFromUsername(message.getTo()), "/chat/receiveChat", message);
            this.template.convertAndSendToUser(sha.getUser().getName(), "/chat/receiveChat", message);
        } catch (NullPointerException e) {
            this.template.convertAndSendToUser(sha.getUser().getName(),
                    "/chat/receiveChat",
                    new RequestFailed(FORBIDDEN, "User " + message.getTo() + " does not exist!"));
        }

    }

    /*@MessageMapping("/getMessages")
    public void sendMessages(Account account) {
        this.template.convertAndSendToUser(this.manager.getUUIDFromUsername(account.getUsername()),
                "/chat/receiveChat",
                this.manager.getChatFromAccount(account));
    }*/
}
