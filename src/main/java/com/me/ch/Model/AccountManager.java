package com.me.ch.Model;

import javax.inject.Named;
import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;

@Named
@Singleton
public class AccountManager {
    private Map<String, Account> accounts;

    public AccountManager() {
        this.accounts = new HashMap<>();
        this.accounts.put("Admin", new Account("Admin", "Admin"));
        this.accounts.put("Username", new Account("Username", "1234"));
    }

    public Account isValidLogin(Account account, String UUID) {
        Account foundAccount = this.accounts.get(account.getUsername());

        if (foundAccount != null)
            if (!foundAccount.getPassword().equals(account.getPassword()))
                return null;

        return foundAccount;
    }

    public String getUUIDFromUsername(String username) {
       return this.accounts.get(username).getUUID();
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

    public void setUUIDForAccount(Account account, String uuid) {
        this.accounts.get(account.getUsername()).setUUID(uuid);
    }
}
