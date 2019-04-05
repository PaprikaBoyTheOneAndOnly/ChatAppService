package com.ch.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class AccountManager {
    private static AccountManager INSTANCE = new AccountManager();
    private static HashMap<String, Account> accounts;

    public static AccountManager getInstance() {
        return INSTANCE;
    }

    private AccountManager() {
        accounts = new HashMap<>();
        accounts.put("Admin", new Account("Admin", "Admin"));
        accounts.put("Username", new Account("Username", ""));
    }

    public static Account isValidLogin(Account requestedAccount) {
        Account foundAccount = accounts.get(requestedAccount.getUsername());

        if(foundAccount != null)
            if(!foundAccount.getPassword().equals(requestedAccount.getPassword()))
                foundAccount = null;

        return foundAccount;
    }

    public static Account createNewAcc(Account newAccount) {
        if(accounts.get(newAccount.getUsername()) != null)
            return null;
        else {
            accounts.put(newAccount.getUsername(), newAccount);
            newAccount.login();
        }

        return newAccount;
    }

    public static boolean accountReceivedMessage(Account account) {
        return false;
    }
}
