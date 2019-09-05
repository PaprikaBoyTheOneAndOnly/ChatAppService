package com.me.ch.Service;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class TestService {

    @GetMapping("/test")
    public String testRequest() {
        return "Server works fine!";
    }
}
