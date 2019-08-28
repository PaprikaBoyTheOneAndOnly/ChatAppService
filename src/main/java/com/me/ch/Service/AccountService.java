package com.me.ch.Service;

import com.me.ch.Model.Account;
import com.me.ch.Model.AccountManager;
import com.me.ch.Service.errors.AccountNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

@CrossOrigin
@RestController
public class AccountService {

    @Inject
    private AccountManager manager;

    public AccountService() {
    }

    @RequestMapping(value = "/isValidLogin")
    public Account validate(@RequestBody Account account) {
        Account foundAccount = this.manager.isValidLogin(account);
        if (foundAccount != null)
            return foundAccount;
        else
            throw new AccountNotFoundException("Account not found");
    }

    @RequestMapping(value = "/createAccount")
    public Account createLogin(@RequestBody Account account) {
        Account foundAccount = this.manager.createAccount(account);

        if (foundAccount != null)
            return foundAccount;
        else
            throw new AccountNotFoundException("Account already exists");
    }

    @GetMapping(value = "/isExistingAccount")
    public boolean isExistingAccount(@RequestParam("username") String username) {
        return this.manager.isExistingAccount(username);
    }
}
