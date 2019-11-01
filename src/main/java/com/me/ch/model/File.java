package com.me.ch.model;

import org.springframework.web.multipart.MultipartFile;

public class File extends Addressable {
    private MultipartFile multipartFile;

    public File() {
        this("", "", null);
    }

    public File(String from, String to, MultipartFile multipartFile) {
        super(from, to);
        this.multipartFile = multipartFile;
    }

    public MultipartFile getMultipartFile() {
        return multipartFile;
    }

    public void setMultipartFile(MultipartFile multipartFile) {
        this.multipartFile = multipartFile;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof File)) return false;
        if (!super.equals(o)) return false;

        File file = (File) o;

        return multipartFile != null ? multipartFile.equals(file.multipartFile) : file.multipartFile == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (multipartFile != null ? multipartFile.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("File{");
        sb.append("multipartFile=").append(multipartFile);
        sb.append(", from='").append(from).append('\'');
        sb.append(", to='").append(to).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
