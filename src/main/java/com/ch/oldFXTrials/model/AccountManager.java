package com.ch.oldFXTrials.model;

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
                new Account("Admin"),
                new Account("Username")
        ));
    }

    public static Account isValidName(String name){
        Account foundAccount = null;

        for (Account account : accounts)
            if (account.getName().equals(name))
                foundAccount = account;

        return foundAccount;
    }

    public void createAccount(String text) {
        accounts.add(new Account(text));
    }
}
