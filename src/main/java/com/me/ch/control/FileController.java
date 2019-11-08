package com.me.ch.control;

import com.me.ch.exception.FileNotFoundException;
import com.me.ch.exception.StorageException;
import com.me.ch.model.File;
import com.me.ch.model.MediaTypeUtils;
import com.me.ch.service.FileSystemStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;

@CrossOrigin
@RestController
public class FileController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ServletContext servletContext;
    @Autowired
    private SimpMessagingTemplate template;
    @Autowired
    private FileSystemStorageService fileSystemStorageService;

    // https://attacomsian.com/blog/uploading-files-spring-boot
    @PostMapping(path = "/sendFile")
    public ResponseEntity uploadFile(@RequestParam("file") MultipartFile file,
                                     @RequestParam("from") String from,
                                     @RequestParam("to") String to) {
        try {
            File fileToSend = fileSystemStorageService.store(file, from, to);

            template.convertAndSendToUser(from, "chat/receiveFile", fileToSend);
            template.convertAndSendToUser(to, "chat/receiveFile", fileToSend);

            return ResponseEntity.ok().build();
        } catch (StorageException e) {
            logger.error("Failed to upload File!", e);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @GetMapping("/downloadFile")
    public ResponseEntity downloadFile(@RequestParam("filename") String filename,
                                       @RequestParam("originalFilename") String originalFilename) {
        try {
            Resource resource = fileSystemStorageService.loadAsResource(filename);
            MediaType mediaType = MediaTypeUtils.getMediaTypeForFileName(this.servletContext, resource.getFilename());
            HttpHeaders headers = new HttpHeaders();
            headers.add("filename", originalFilename);

            return ResponseEntity
                    .ok()
                    .headers(headers)
                    .contentType(mediaType)
                    .body(resource);
        } catch (FileNotFoundException e) {
            logger.error("Could not find file " + filename + "!", e);
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Could not find file " + filename + "!");
        }
    }
}
