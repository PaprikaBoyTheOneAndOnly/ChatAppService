package com.me.ch.model;

import com.me.ch.repository.AccountRepository;
import com.me.ch.repository.DbAccount;
import com.me.ch.repository.DbMessage;
import com.me.ch.repository.MessageRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
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
        ArrayList<DbMessage> dbMessages = new ArrayList<>();
        DbMessage dbMessage1 = new DbMessage("Admin", "User 2", "Hello There");
        dbMessages.add(dbMessage1);
        dbMessages.add(new DbMessage("User 2", "Admin", "General Kenobi"));
        dbMessages.add(new DbMessage("User 3", "Admin", "Hello gamer"));
        when(messageRepository.getAllMessages(anyString())).thenReturn(dbMessages);

        List<Chat> adminsChats = this.accountManager.getChatsFromAccount("Admin");
        assertThat(adminsChats, is(not(empty())));
        assertThat(adminsChats.size(), is(2));
        assertThat(adminsChats.get(0).getChatWith(), is(dbMessage1.getToUser()));

        assertThat(adminsChats.get(0).getMessages().get(0).getTo(), is(dbMessage1.getToUser()));
        assertThat(adminsChats.get(0).getMessages().get(0).getFrom(), is(dbMessage1.getFromUser()));

        assertThat(adminsChats.get(0).getMessages().get(1).getFrom(), is(dbMessage1.getToUser()));
        assertThat(adminsChats.get(0).getMessages().get(1).getTo(), is(dbMessage1.getFromUser()));
    }

    @Test
    public void isExistingAccount() {
        when(accountRepository.existsById(anyString())).thenReturn(true);
        assertThat(this.accountManager.isExistingAccount("Admin"), is(true));
    }

    @Test
    public void isExistingAccount_notExisting() {
        when(accountRepository.existsById(anyString())).thenReturn(false);
        assertThat(this.accountManager.isExistingAccount("Admin 2"), is(false));
    }
}
