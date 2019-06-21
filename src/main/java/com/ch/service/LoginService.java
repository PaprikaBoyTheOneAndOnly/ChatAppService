package com.ch.service;

import com.ch.model.Account;
import com.ch.model.AccountManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component
public class LoginService {
    @Autowired
    private SimpMessagingTemplate template;

    @Inject
    private AccountManager manager;

    public LoginService() {
    }

    @MessageMapping("/validate")
    public void setLogin(SimpMessageHeaderAccessor sha, Account account) {
        System.out.println("here");
        System.out.println("Loginrequest with Account:  " + account.toString());
        template.convertAndSendToUser(sha.getUser().getName(), "/setLogin", this.manager.isValidLogin(account));
    }
}
