package com.ch.service;

import com.ch.model.Account;
import com.ch.model.AccountManager;
import org.jboss.logging.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component()
public class LoginService {
    @Autowired
    private SimpMessagingTemplate template;

    @Inject
    private AccountManager manager;

    public LoginService() {
    }

    @MessageMapping("/validateLogin")
    public void login(SimpMessageHeaderAccessor accessor, Account account) {
        System.out.println("Loginrequest with Account:  " + account.toString());
        template.convertAndSendToUser(accessor.getUser().getName(), "/user/setLogin", this.manager.isValidLogin(account));
    }
}
