package com.ch.model;

import java.util.HashMap;


public class AccountManager {
    private static final AccountManager INSTANCE = new AccountManager();
    private HashMap<String, Account> accounts;

    public static AccountManager getInstance() {
        return INSTANCE;
    }

    private AccountManager() {
        accounts = new HashMap<>();
        accounts.put("Admin", new Account("Admin", "Admin"));
        accounts.put("Username", new Account("Username", ""));
    }

    public Account isValidLogin(Account requestedAccount) {
        Account foundAccount = accounts.get(requestedAccount.getUsername());

        if (foundAccount != null)
            if (!foundAccount.getPassword().equals(requestedAccount.getPassword()))
                foundAccount = null;

        return foundAccount;
    }

    public Account createNewAcc(Account newAccount) {
        if (accounts.get(newAccount.getUsername()) != null)
            return null;
        else {
            accounts.put(newAccount.getUsername(), newAccount);
            newAccount.login();
        }

        return newAccount;
    }

    public boolean accountReceivedMessage(Account account) {
        return false;
    }
}
