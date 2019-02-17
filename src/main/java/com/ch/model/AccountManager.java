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

    public static Account isValidLogin(String name, String password) {
        Account foundAccount = null;

        for (Account account : accounts)
            if (account.getUsername().equals(name) && account.getPassword().equals(password)){
                foundAccount = account;
                foundAccount.login();
            }

        return foundAccount;
    }

    public void createAccount(String name, String password) {
        accounts.add(new Account(name, password));
    }
}
