package com.me.ch.configuration;

import com.me.ch.config.StorageProperties;
import com.me.ch.service.AccountService;
import com.me.ch.service.FileSystemStorageService;
import com.me.ch.service.MessageService;
import org.springframework.context.annotation.Bean;

public class MyTestConfiguration {
    @Bean
    public FileSystemStorageService fileSystemStorageService() {
        StorageProperties properties = new StorageProperties("root");
        return new FileSystemStorageService(properties);
    }
    @Bean
    public AccountService accountService() {
        return new AccountService();
    }
    @Bean
    public MessageService messageService() {
        return new MessageService();
    }
}
