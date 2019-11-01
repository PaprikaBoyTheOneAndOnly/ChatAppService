package com.me.ch.service;

import com.me.ch.configuration.MyTestConfiguration;
import com.me.ch.model.Account;
import com.me.ch.repository.AccountRepository;
import com.me.ch.repository.AccountEntity;
import com.me.ch.repository.MessageEntity;
import com.me.ch.repository.MessageRepository;
import com.me.ch.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@Import(MyTestConfiguration.class)
@RunWith(SpringRunner.class)
public class AccountServiceTest {
    @Autowired
    private AccountService accountService;

    @MockBean
    private AccountRepository accountRepository;

    @MockBean
    private Logger logger;

    @Test
    public void isValidLogin() {
        when(accountRepository.findValidAccount(anyString(), anyString())).thenReturn(new AccountEntity("Admin", "1234"));
        assertThat(this.accountService.isValidLogin(new Account("Admin", "Admin")),
                is(new Account("Admin", "1234")));
    }

    @Test
    public void isValidLogin_noAccountFound() {
        assertThat(this.accountService.isValidLogin(new Account("not an existing account", "password")), nullValue());
    }

    @Test
    public void isValidLogin_wrongPassword() {
        assertThat(this.accountService.isValidLogin(new Account("Admin", "wrongPassword")), nullValue());
    }

    @Test
    public void createAccount() throws SQLException {
        when(accountRepository.existsById(anyString())).thenReturn(false);
        Account accountToAdd = new Account("Peter", "password");

        assertThat(this.accountService.createAccount(accountToAdd), is(accountToAdd));
        verify(accountRepository, times(1)).addAccount(anyString(), anyString());
    }

    @Test
    public void createAccount_usernameAlreadyExists() {
        when(accountRepository.existsById(anyString())).thenReturn(true);
        assertThat(this.accountService.createAccount(new Account("Admin", "somePassword")), nullValue());
        verify(logger, times(0)).error("Duplicated Account \"Admin\" tried to be added to Database!");
    }

    @Test
    public void createAccount_usernameAlreadyExists_errored() throws SQLException {
        when(accountRepository.existsById(anyString())).thenReturn(false);
        doThrow(SQLException.class)
                .when(accountRepository)
                .addAccount(anyString(), anyString());

        assertThat(this.accountService.createAccount(new Account("Admin", "somePassword")), nullValue());
        verify(logger, times(1)).error("Duplicated Account \"Admin\" tried to be added to Database!");
    }

    @Test
    public void isExistingAccount() {
        when(accountRepository.existsById(anyString())).thenReturn(true);
        assertThat(this.accountService.isExistingAccount("Admin"), is(true));
    }

    @Test
    public void isExistingAccount_notExisting() {
        when(accountRepository.existsById(anyString())).thenReturn(false);
        assertThat(this.accountService.isExistingAccount("Admin 2"), is(false));
    }
}
