package com.me.ch.Model;

import com.me.ch.Model.dao.AccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Component("accountManager")
@ApplicationScope
public class AccountManager {
    private Map<String, Account> accounts;

    @Autowired
    private AccountDao accountDao;

    public AccountManager() {
        this.accounts = new HashMap<>();
        this.accountDao.getAll().forEach(account -> this.accounts.put(account.getUsername(), account));
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

    public AccountDao getdao() {
            return this.accountDao;
    }
}
