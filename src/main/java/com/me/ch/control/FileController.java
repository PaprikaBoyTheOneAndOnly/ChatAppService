package com.me.ch.control;

import com.me.ch.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@CrossOrigin
@RestController
public class FileController {
    @Autowired
    private StorageService storageService;

    // https://attacomsian.com/blog/uploading-files-spring-boot
    @PostMapping(path= "/sendFile")
    public void uploadFile(@RequestParam("file") MultipartFile file) {
        String name = storageService.store(file);
        String uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")
                .path(name)
                .toUriString();
        System.out.println(uri);

    }

    @GetMapping("/downloadFile")
    public Resource downloadFile(@RequestParam("filename") String filename) {
        Resource resource = storageService.loadAsResource(filename);

        return resource;
    }
}
