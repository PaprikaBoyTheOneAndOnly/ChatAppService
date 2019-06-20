package com.ch.model;

import javax.inject.Named;
import java.util.HashMap;

@Named
public class AccountManager {
    private HashMap<String, Account> accounts;

    public AccountManager() {
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
}
