package com.me.ch.service;

import com.me.ch.model.Account;
import com.me.ch.repository.AccountEntity;
import com.me.ch.repository.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

import java.sql.SQLException;

@ApplicationScope
@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public AccountService() {
    }

    public Account isValidLogin(Account account) {
        AccountEntity foundAccount = accountRepository.findValidAccount(account.getUsername(), account.getPassword());

        return foundAccount == null ? null : new Account(foundAccount.getUsername(), foundAccount.getPassword());
    }

    public Account createAccount(Account account) {
        if (!this.isExistingAccount(account.getUsername())) {
            try {
                accountRepository.addAccount(account.getUsername(), account.getPassword());
                return account;
            } catch (SQLException e) {
                logger.error("Duplicated Account \"" + account.getUsername() + "\" tried to be added to Database!");
            }
        }
        return null;
    }

    public boolean isExistingAccount(String username) {
        return this.accountRepository.existsById(username);
    }

}
