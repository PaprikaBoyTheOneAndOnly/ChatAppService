package com.me.ch.model;

import com.me.ch.repository.AccountRepository;
import com.me.ch.repository.DbAccount;
import com.me.ch.repository.DbMessage;
import com.me.ch.repository.MessageRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class AccountManagerTest {

    @TestConfiguration
    static class EmployeeServiceImplTestContextConfiguration {

        @Bean
        public AccountManager accountManager() {
            return new AccountManager();
        }
    }

    @Autowired
    private AccountManager accountManager;

    @MockBean
    private AccountRepository accountRepository;
    @MockBean
    private MessageRepository messageRepository;
    @MockBean
    private Logger logger;

    @Test
    public void isValidLogin() {
        when(accountRepository.findValidAccount(anyString(), anyString())).thenReturn(new DbAccount("Admin", "1234"));
        assertThat(this.accountManager.isValidLogin(new Account("Admin", "Admin")),
                is(new Account("Admin", "1234")));
    }

    @Test
    public void isValidLogin_noAccountFound() {
        assertThat(this.accountManager.isValidLogin(new Account("not an existing account", "password")), nullValue());
    }

    @Test
    public void isValidLogin_wrongPassword() {
        assertThat(this.accountManager.isValidLogin(new Account("Admin", "wrongPassword")), nullValue());
    }

    @Test()
    public void createAccount() throws SQLException {
        when(accountRepository.existsById(anyString())).thenReturn(false);
        Account accountToAdd = new Account("Peter", "password");

        assertThat(this.accountManager.createAccount(accountToAdd), is(accountToAdd));
        verify(accountRepository, times(1)).addAccount(anyString(), anyString());
    }

    @Test
    public void createAccount_usernameAlreadyExists() {
        when(accountRepository.existsById(anyString())).thenReturn(true);
        assertThat(this.accountManager.createAccount(new Account("Admin", "somePassword")), nullValue());
        verify(logger, times(0)).error("Duplicated Account \"Admin\" tried to be added to Database!");
    }

    @Test
    public void createAccount_usernameAlreadyExists_errored() throws SQLException {
        when(accountRepository.existsById(anyString())).thenReturn(false);
        doThrow(SQLException.class)
                .when(accountRepository)
                .addAccount(anyString(), anyString());

        assertThat(this.accountManager.createAccount(new Account("Admin", "somePassword")), nullValue());
        verify(logger, times(1)).error("Duplicated Account \"Admin\" tried to be added to Database!");
    }

    @Test
    public void addMessage() {
        this.accountManager.addMessage(new Message("Admin", "User", "Hello There!"));
        verify(messageRepository, times(1)).save(new DbMessage("Admin", "User", "Hello There!"));
    }

    @Test
    public void getChatsFromAccount() {
        assertThat(this.accountManager.getAccounts(), hasKey("Admin"));
        assertThat(this.accountManager.getAccounts().get("Admin").getChats(), is(empty()));
        assertThat(this.accountManager.getAccounts().get("Admin").getChats(), is(empty()));
        this.accountManager.getAccounts().get("Admin").addMessage(new Message());
        assertThat(this.accountManager.getAccounts().get("Admin").getChats(), is(not(empty())));

    }

    /*@Test
    public void isExistingAccount() {
        assertThat(this.accountManager.isExistingAccount("Admin"), is(true));
    }

    @Test
    public void isExistingAccount_notExisting() {
        assertThat(this.accountManager.isExistingAccount("Admin 2"), is(false));
    }*/
}
