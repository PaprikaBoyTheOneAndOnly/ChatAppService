package com.me.ch.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class CleanUpService {
    @Autowired
    private FileSystemStorageService fileSystemStorageService;

    @PostConstruct
    public void cleanUp() {
        fileSystemStorageService.deleteAll();
        fileSystemStorageService.init();
    }
}
