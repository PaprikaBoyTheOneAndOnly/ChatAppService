package com.me.ch.Service;

import com.me.ch.repository.AccountRepository;
import com.me.ch.repository.DbAccount;
import com.mysql.cj.jdbc.exceptions.SQLError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

@CrossOrigin
@RestController
public class TestService {
    @Autowired
    private AccountRepository accountRepository;
    @GetMapping("/test")
    public String testRequest() {
        System.out.println(accountRepository.existsById("Admin"));
        return "Server works fine!";
    }
}
