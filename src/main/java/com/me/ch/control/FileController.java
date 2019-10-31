package com.me.ch.control;

import com.me.ch.exception.FileNotFoundException;
import com.me.ch.exception.StorageException;
import com.me.ch.service.FileSystemStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private FileSystemStorageService fileSystemStorageService;

    // https://attacomsian.com/blog/uploading-files-spring-boot
    @PostMapping(path = "/sendFile")
    public ResponseEntity uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            String name = fileSystemStorageService.store(file);
            String uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/download/")
                    .path(name)
                    .toUriString();

            return ResponseEntity.ok().build();
        } catch (StorageException e) {
            logger.error("Failed to upload File!", e);
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }
    }

    @GetMapping("/downloadFile")
    public ResponseEntity<Resource> downloadFile(@RequestParam("filename") String filename) {
        try {
            Resource resource = fileSystemStorageService.loadAsResource(filename);
            return ResponseEntity
                    .ok()
                    .body(resource);
        } catch (FileNotFoundException e) {
            logger.debug("Could not find file " + filename + "!");
            return ResponseEntity
                    .notFound()
                    .build();
        }
    }
}
