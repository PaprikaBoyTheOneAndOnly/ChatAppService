package com.me.ch.model;

import org.springframework.web.multipart.MultipartFile;

public class File extends Addressable {
    private String filename;
    private String originalFilename;

    public File() {
        this("", "", "", "");
    }

    public File(String from, String to, String filename, String originalFilename) {
        super(from, to);
        this.filename = filename;
        this.originalFilename =  originalFilename;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getOriginalFilename() {
        return originalFilename;
    }

    public void setOriginalFilename(String originalFilename) {
        this.originalFilename = originalFilename;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof File)) return false;
        if (!super.equals(o)) return false;

        File file = (File) o;

        if (!filename.equals(file.filename)) return false;
        return originalFilename.equals(file.originalFilename);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + filename.hashCode();
        result = 31 * result + originalFilename.hashCode();
        return result;
    }
}
