package com.me.ch.Service;

import com.me.ch.Service.errors.AccountNotFoundException;
import com.me.ch.model.Account;
import com.me.ch.model.AccountManager;
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
public class AccountService {

    @Autowired
    private AccountManager accountManager;

    public AccountService() {
    }

    @RequestMapping(value = "/isValidLogin")
    public Account validate(@RequestBody Account account) {
        Account foundAccount = this.accountManager.isValidLogin(account);
        if (foundAccount != null)
            return foundAccount;
        else
            throw new AccountNotFoundException("Account not found");
    }

    @RequestMapping(value = "/createAccount")
    public Account createLogin(@RequestBody Account account) {
        Account foundAccount = this.accountManager.createAccount(account);

        if (foundAccount != null)
            return foundAccount;
        else
            throw new AccountNotFoundException("Account already exists");
    }

    @GetMapping("/loadChats")
    public ArrayList<Chat> loadChats(@RequestParam("username") String username) {
        return this.accountManager.getChatsFromAccount(username);
    }

    @GetMapping(value = "/isExistingAccount")
    public boolean isExistingAccount(@RequestParam("username") String username) {
        return this.accountManager.isExistingAccount(username);
    }
}
