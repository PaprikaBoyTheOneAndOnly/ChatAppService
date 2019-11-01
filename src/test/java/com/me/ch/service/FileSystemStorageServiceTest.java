package com.me.ch.service;

import com.me.ch.config.StorageProperties;
import com.me.ch.model.File;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class FileSystemStorageServiceTest {
    @TestConfiguration
    static class ConfigClass {
        @Bean
        public FileSystemStorageService fileSystemStorageServiceTest() {
            return new FileSystemStorageService(new StorageProperties("root"));
        }
    }

    @Autowired
    private FileSystemStorageService fileSystemStorageService;

    @Test
    public void renameFile() {
        byte[] content = {1, 2, 2};
        assertThat(fileSystemStorageService.store(
                new MockMultipartFile("file", "test.file.pdf", "pdf", content),
                "Admin", "Username"))
                .isEqualTo("test.file-Admin-Username.pdf");
    }
}
