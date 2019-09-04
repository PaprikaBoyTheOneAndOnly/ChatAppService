package com.me.ch.Model;


import com.me.ch.Model.dao.AccountDao;
import com.me.ch.app.ApplicationConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

import java.util.ArrayList;

import static org.mockito.Mockito.when;

@SpringBootTest(classes = ApplicationConfig.class)
@ComponentScan("com.me.ch")
class AccountManagerTest {

    @Mock
    private AccountDao accountDao;

    @InjectMocks
    private AccountManager accountManager = new AccountManager();

    @BeforeEach
    public void setUp() {
        this.accountManager = new AccountManager();
        System.out.println(accountManager.getdao());
        when(accountDao.getAll()).thenReturn(new ArrayList<>());

    }

    @Test
    public void test() {
        System.out.println(accountManager.getdao());
    }

}
