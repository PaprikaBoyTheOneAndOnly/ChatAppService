package com.me.ch.control;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class HealthCheckController {

    @GetMapping("/healthz")
    public String testRequest() {
        return "Server works fine!";
    }
}
