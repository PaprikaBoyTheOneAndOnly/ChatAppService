package com.me.ch.repository;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "file")
public class FileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String media_type;
    private String filename;
    private String original_filename;
    private LocalDateTime sent_time;
    private String from_user;
    private String to_user;

    public FileEntity() {
        this("", "","", "" ,"", LocalDateTime.now());
    }

    public FileEntity(String from_user, String to_user, String filename, String original_filename, String media_type, LocalDateTime sent_time) {
        this.media_type = media_type;
        this.filename = filename;
        this.original_filename = original_filename;
        this.sent_time = sent_time;
        this.from_user = from_user;
        this.to_user = to_user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMedia_type() {
        return media_type;
    }

    public void setMedia_type(String media_type) {
        this.media_type = media_type;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getOriginal_filename() {
        return original_filename;
    }

    public void setOriginal_filename(String original_filename) {
        this.original_filename = original_filename;
    }

    public LocalDateTime getSent_time() {
        return sent_time;
    }

    public void setSent_time(LocalDateTime sent_time) {
        this.sent_time = sent_time;
    }

    public String getFrom_user() {
        return from_user;
    }

    public void setFrom_user(String from_user) {
        this.from_user = from_user;
    }

    public String getTo_user() {
        return to_user;
    }

    public void setTo_user(String to_user) {
        this.to_user = to_user;
    }
}
