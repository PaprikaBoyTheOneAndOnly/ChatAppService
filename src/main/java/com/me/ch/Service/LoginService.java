package com.me.ch.Service;

import com.me.ch.Model.Account;
import com.me.ch.Model.AccountManager;
import com.me.ch.Model.RequestFailed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import javax.inject.Inject;

import static com.me.ch.Model.RequestFailed.RequestCode.FORBIDDEN;

@Controller
public class LoginService {
    @Autowired
    private SimpMessagingTemplate template;

    @Inject
    private AccountManager manager;

    public LoginService() {
    }

    @MessageMapping("/validate")
    public void validate(SimpMessageHeaderAccessor sha, Account account) {
        Account foundAccount = this.manager.isValidLogin(account, sha.getUser().getName());
        System.out.println(account.getUsername() +": ");
        System.out.println(sha.getUser().getName());
        if (foundAccount != null)
            template.convertAndSendToUser(sha.getUser().getName(), "login/setLogin", foundAccount);
        else
            template.convertAndSendToUser(sha.getUser().getName(), "login/setLogin", new RequestFailed(FORBIDDEN, "No such account found!"));
    }

    @MessageMapping("/createAccount")
    public void createLogin(SimpMessageHeaderAccessor sha, Account account) {
        Account foundAccount = this.manager.createAccount(account);

        if (foundAccount != null)
            template.convertAndSendToUser(sha.getUser().getName(), "login/setLogin", foundAccount);
        else
            template.convertAndSendToUser(sha.getUser().getName(), "login/setLogin", new RequestFailed(FORBIDDEN, "Account already exists!"));
    }
}
