package com.me.ch.Model;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Component("accountManager")
@ApplicationScope
public class AccountManager {
    private Map<String, Account> accounts;

    public AccountManager() {
        this.accounts = new HashMap<>();
        this.accounts.put("Admin", new Account("Admin", "Admin"));
        this.accounts.put("Username", new Account("Username", "1234"));
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
        } else
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
