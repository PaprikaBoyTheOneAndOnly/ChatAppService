package com.me.ch.Service;

import com.me.ch.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class HealthCheckService {

    @GetMapping("/healthz")
    public String testRequest() {
        return "Server works fine!";
    }
}
