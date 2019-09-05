package com.me.ch.model;

import com.me.ch.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;

import static org.hamcrest.MatcherAssert.assertThat;


@RunWith(SpringRunner.class)
public class AccountManagerTest {

    @TestConfiguration
    static class EmployeeServiceImplTestContextConfiguration {

        @Bean
        public AccountManager accountManager() {
            System.out.println("jere");
            return new AccountManager();
        }
    }

    @Autowired
    private AccountManager accountManager;

    @MockBean
    private AccountRepository accountRepository;

    @BeforeEach
    public void setUp() {
        HashMap<String, Account> accounts = new HashMap<>();
        accounts.put("Admin", new Account("Admin", "1234"));

        this.accountManager.setAccounts(accounts);
    }

    @Test
    public void isValidLogin() {
        assertThat(this.accountManager.isValidLogin(new Account("Admin", "1234")), is("sdf"));
    }
}
