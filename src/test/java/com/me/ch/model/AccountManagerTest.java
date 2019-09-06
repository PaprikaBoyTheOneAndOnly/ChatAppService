package com.me.ch.model;

import com.me.ch.repository.AccountRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

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

    @Before
    public void setUp() {
        HashMap<String, Account> accounts = new HashMap<>();
        accounts.put("Admin", new Account("Admin", "1234"));
        accounts.put("User 2", new Account("User 2", "1234"));

        this.accountManager.setAccounts(accounts);
    }

    @Test
    public void isValidLogin() {
        assertThat(new Account("Admin", "1234"), is(this.accountManager.isValidLogin(new Account("Admin", "1234"))));
    }

    @Test
    public void isValidLogin_noAccountFound() {
        assertThat(this.accountManager.isValidLogin(new Account("not an existing account", "password")), nullValue());
    }

    @Test
    public void isValidLogin_wrongPassword() {
        assertThat(this.accountManager.getAccounts(), hasKey("Admin"));
        assertThat(this.accountManager.isValidLogin(new Account("Admin", "wrongPassword")), nullValue());
    }

    @Test
    public void createAccount() {
        Account accountToAdd = new Account("Peter", "password");

        assertThat(this.accountManager.createAccount(accountToAdd), is(accountToAdd));
    }

    @Test
    public void createAccount_usernameAlreadyExists() {
        assertThat(this.accountManager.getAccounts(), hasEntry("Admin", new Account("Admin", "1234")));
        assertThat(this.accountManager.createAccount(new Account("Admin", "somePassword")), nullValue());
    }
}
