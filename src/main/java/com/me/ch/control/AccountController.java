package com.me.ch.control;

import com.me.ch.exception.AccountNotFoundException;
import com.me.ch.model.Account;
import com.me.ch.service.AccountService;
import com.me.ch.model.Chat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@CrossOrigin
@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;

    public AccountController() {
    }

    @RequestMapping(value = "/isValidLogin")
    public Account validate(@RequestBody Account account) {
        Account foundAccount = this.accountService.isValidLogin(account);
        if (foundAccount != null)
            return foundAccount;
        else
            throw new AccountNotFoundException("Account not found");
    }

    @RequestMapping(value = "/createAccount")
    public Account createLogin(@RequestBody Account account) {
        Account foundAccount = this.accountService.createAccount(account);

        if (foundAccount != null)
            return foundAccount;
        else
            throw new AccountNotFoundException("Account already exists");
    }

    @GetMapping("/loadChats")
    public ArrayList<Chat> loadChats(@RequestParam("username") String username) {
        return this.accountService.getChatsFromAccount(username);
    }

    @GetMapping(value = "/isExistingAccount")
    public boolean isExistingAccount(@RequestParam("username") String username) {
        return this.accountService.isExistingAccount(username);
    }
}
