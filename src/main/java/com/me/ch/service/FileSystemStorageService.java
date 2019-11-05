package com.me.ch.service;

import com.me.ch.config.StorageProperties;
import com.me.ch.exception.FileNotFoundException;
import com.me.ch.exception.StorageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * Documentation:
 * https://attacomsian.com/blog/uploading-files-spring-boot
 */
@ApplicationScope
@Service
public class FileSystemStorageService {

    private final Path rootLocation;

    @Autowired
    public FileSystemStorageService(StorageProperties properties) {
        this.rootLocation = Paths.get(properties.getLocation());
    }

    @PostConstruct
    public void init() {
        try {
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            throw new StorageException("Could not initialize storage location", e);
        }
    }

    public String store(MultipartFile multipartFile, String from, String to) throws StorageException {
        String filename = this.renameFile(multipartFile, from, to);
        try {
            if (multipartFile.isEmpty()) {
                throw new StorageException("Failed to store empty file " + filename);
            }
            if (filename.contains("..")) {
                throw new StorageException(
                        "Cannot store file with relative path outside current directory "
                                + filename);
            }
            try (InputStream inputStream = multipartFile.getInputStream()) {
                Files.copy(inputStream, this.rootLocation.resolve(filename),
                        StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (IOException e) {
            throw new StorageException("Failed to store file " + filename, e);
        }

        return filename;
    }

    public Path load(String filename) {
        return rootLocation.resolve(filename);
    }

    public Resource loadAsResource(String filename) throws FileNotFoundException {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new FileNotFoundException("Could not read file: " + filename);
            }
        } catch (MalformedURLException e) {
            throw new FileNotFoundException("Could not read file: " + filename, e);
        }
    }

    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }

    private String renameFile(MultipartFile file, String form, String to) {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        String[] nameChars = filename.split("");
        StringBuilder builder = new StringBuilder();

        int typeLength = 0;
        for (int i = nameChars.length - 1; i > 0; typeLength++, i--) {
            if (nameChars[i].equals(".")) {
                break;
            }
        }


        for (int i = 0; i < nameChars.length - (1 + typeLength); i++) {
            builder.append(nameChars[i]);
        }

        builder.append("-");
        builder.append(form);
        builder.append("-");
        builder.append(to);
        builder.append(".");

        for(int i = 0; i < typeLength; i++) {
            builder.append(nameChars[nameChars.length-(typeLength-i)]);
        }

        return builder.toString();
    }
}
