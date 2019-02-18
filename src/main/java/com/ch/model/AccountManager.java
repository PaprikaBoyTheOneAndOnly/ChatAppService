package com.ch.model;

import java.util.ArrayList;
import java.util.Arrays;

public class AccountManager {
    private static AccountManager INSTANCE = new AccountManager();
    private static ArrayList<Account> accounts;

    public static AccountManager getInstance() {
        return INSTANCE;
    }

    private AccountManager() {
        accounts = new ArrayList<>();
        accounts.addAll(Arrays.asList(
                new Account("Admin", "Admin"),
                new Account("Username", "")
        ));
    }

    public static Account isValidLogin(Account requestedAccount) {
        Account foundAccount = null;

        for (Account account : accounts)
            if (account.getUsername().equals(requestedAccount.getUsername())
                    && account.getPassword().equals(requestedAccount.getPassword())){
                foundAccount = account;
            }

        return foundAccount;
    }

    public static Account createNewAcc(Account newAccount) {
        if(isValidLogin(newAccount) == null)
            accounts.add(newAccount);
        else
            newAccount = null;

        return newAccount;
    }
}
