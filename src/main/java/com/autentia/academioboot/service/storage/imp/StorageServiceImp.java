package com.autentia.academioboot.service.storage.imp;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import com.autentia.academioboot.exception.StorageException;
import com.autentia.academioboot.service.storage.StorageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

@Service
public class StorageServiceImp implements StorageService {

    private Path root;

    @Autowired
    public StorageServiceImp(@Value("${storage.service.root}") String root) {
        this.root = Paths.get(root);
    }

    public void init() {
        try {
            Files.createDirectories(root);
        } catch (IOException e) {
            throw new StorageException("Could not initialize storage", e);
        }
    }

    public void store(InputStream inputStream, String filename) {
        try {
            if (filename.contains("..")) {
                throw new StorageException("Relative path found in file: " + filename);
            }

            Files.copy(inputStream, this.root.resolve(filename), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new StorageException("Failed to store file: " + filename);
        }

    }

    public Path load(String filename) {
        return this.root.resolve(filename);
    }

    public Resource loadAsResource(String filename) {
        Path file = load(filename);
        try {
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new StorageException("Cannot read file: " + filename);
            }
        } catch (MalformedURLException e) {
            throw new StorageException("Cannot read file: " + filename);
        }
    }

}