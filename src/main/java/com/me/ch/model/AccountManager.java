package com.me.ch.model;

import com.me.ch.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@ApplicationScope
@Service("accountManager")
public class AccountManager {
    @Autowired
    private AccountRepository accountRepository;

    private Map<String, Account> accounts;

    public AccountManager() {
        this.accounts = new HashMap<>();
    }

    public void setAccounts(Map<String, Account> accounts) {
        this.accounts = accounts;
    }

    public Account isValidLogin(Account account) {
        Account foundAccount = this.accounts.get(account.getUsername());

        if (foundAccount != null)
            if (!foundAccount.getPassword().equals(account.getPassword()))
                return null;

        return foundAccount;
    }

    public Account createAccount(Account account) {
        if (this.accounts.get(account.getUsername()) == null) {
            this.accounts.put(account.getUsername(), account);
            return account;
        }

            return null;
    }

    public void addMessageToAccounts(Message message) throws NullPointerException {
        this.accounts.get(message.getTo()).addMessage(message);
        this.accounts.get(message.getFrom()).addMessage(message);
    }

    public ArrayList<Chat> getChatsFromAccount(String username) {
        return this.accounts.get(username).getChats();
    }

    public boolean isExistingAccount(String username) {
        return this.accounts.containsKey(username);
    }
}
