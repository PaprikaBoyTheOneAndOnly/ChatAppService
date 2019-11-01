package com.me.ch.control;

import com.me.ch.exception.StorageException;
import com.me.ch.service.FileSystemStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin
@RestController
public class FileController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SimpMessagingTemplate template;
    @Autowired
    private FileSystemStorageService fileSystemStorageService;

    // https://attacomsian.com/blog/uploading-files-spring-boot
    @PostMapping(path = "/sendFile")
    public void uploadFile(@RequestParam("file") MultipartFile file,
                           @RequestParam("from") String from,
                           @RequestParam("to") String to) {
        try {
            fileSystemStorageService.store(file, from, to);
        }catch (StorageException e) {
            logger.error("Failed to upload File!", e);
        }
    }

   /* @GetMapping("/downloadFile")
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
    }*/
}
