package com.me.ch.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class File extends Addressable {
    private String filename;
    private String originalFilename;
    private String mediaType;

    public File() {
        this("", "", "", "", "", LocalDateTime.now());
    }


    public File(String from, String to, String filename, String originalFilename) {
        this(from, to,filename,originalFilename,"");
    }

    public File(String from, String to, String filename, String originalFilename, String mediaType) {
        this(from, to,filename,originalFilename,mediaType,LocalDateTime.now());
    }

    public File(String from, String to, String filename, String originalFilename, String mediaType, LocalDateTime timeSent) {
        super(from, to, timeSent);
        this.filename = filename;
        this.originalFilename = originalFilename;
        this.mediaType = mediaType;
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

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof File)) return false;
        if (!super.equals(o)) return false;

        File file = (File) o;

        if (!Objects.equals(filename, file.filename)) return false;
        if (!Objects.equals(originalFilename, file.originalFilename))
            return false;
        return Objects.equals(mediaType, file.mediaType);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (filename != null ? filename.hashCode() : 0);
        result = 31 * result + (originalFilename != null ? originalFilename.hashCode() : 0);
        result = 31 * result + (mediaType != null ? mediaType.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "File{" + "filename='" + filename + '\'' +
                ", originalFilename='" + originalFilename + '\'' +
                ", mediaType='" + mediaType + '\'' +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", timeSent=" + timeSent +
                '}';
    }
}
