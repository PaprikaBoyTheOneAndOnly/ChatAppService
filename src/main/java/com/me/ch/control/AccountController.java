package com.me.ch.control;

import com.me.ch.exception.AccountNotFoundException;
import com.me.ch.model.Account;
import com.me.ch.model.Chat;
import com.me.ch.service.AccountService;
import com.me.ch.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
    @Autowired
    private MessageService messageService;

    public AccountController() {
    }

    @PostMapping(value = "/isValidLogin")
    public ResponseEntity<Account> validate(@RequestBody Account account) {
        Account foundAccount = this.accountService.isValidLogin(account);

        if (foundAccount != null) {
            return ResponseEntity
                    .ok()
                    .body(foundAccount);
        }

        throw new AccountNotFoundException("Account not found");
    }

    @PostMapping(value = "/createAccount")
    public ResponseEntity<Account> createLogin(@RequestBody Account account) {
        Account foundAccount = this.accountService.createAccount(account);

        if (foundAccount != null) {
            return ResponseEntity
                    .ok()
                    .body(foundAccount);
        }

        throw new AccountNotFoundException("Account not found");
    }

    @GetMapping(value = "/isExistingAccount")
    public ResponseEntity<Boolean> isExistingAccount(@RequestParam("username") String username) {
        return ResponseEntity.ok().body(this.accountService.isExistingAccount(username));
    }


    @GetMapping("/loadChats")
    public ResponseEntity<ArrayList<Chat>> returnChats(@RequestParam("username") String username) {
        return ResponseEntity.ok().body(this.messageService.getChatsFromAccount(username));
    }
}
