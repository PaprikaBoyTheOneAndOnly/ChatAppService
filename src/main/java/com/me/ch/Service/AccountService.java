package com.me.ch.Service;

import com.me.ch.Service.errors.AccountNotFoundException;
import com.me.ch.model.Account;
import com.me.ch.model.AccountManager;
import com.me.ch.model.Chat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;

@CrossOrigin
@RestController
public class AccountService {

    @Autowired
    private AccountManager accountManager;

    public AccountService() {
    }

    @RequestMapping(value = "/isValidLogin")
    public Account validate(@RequestBody Account account) {
        Account foundAccount = this.accountManager.isValidLogin(account);
        if (foundAccount != null)
            return foundAccount;
        else
            throw new AccountNotFoundException("Account not found");
    }

    @RequestMapping(value = "/createAccount")
    public Account createLogin(@RequestBody Account account) {
        Account foundAccount = this.accountManager.createAccount(account);

        if (foundAccount != null)
            return foundAccount;
        else
            throw new AccountNotFoundException("Account already exists");
    }

    @GetMapping("/loadChats")
    public ArrayList<Chat> loadChats(@RequestParam("username") String username) {
        return this.accountManager.getChatsFromAccount(username);
    }

    @GetMapping(value = "/isExistingAccount")
    public boolean isExistingAccount(@RequestParam("username") String username) {
        return this.accountManager.isExistingAccount(username);
    }

    // https://www.devglan.com/spring-boot/spring-boot-file-upload-download
    @PostMapping(path= "/sendFile")
    public ResponseEntity<String> handleFile(@RequestParam("file") MultipartFile file) {
        Document doc = new Document();
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        doc.setDocName(fileName);
        try {
            doc.setFile(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        documentDao.save(doc);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/files/download/")
                .path(fileName).path("/db")
                .toUriString();
        return ResponseEntity.ok(fileDownloadUri);
    }
}

@Entity
class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String docName;

    @Column
    @Lob
    private byte[] file;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }
}
