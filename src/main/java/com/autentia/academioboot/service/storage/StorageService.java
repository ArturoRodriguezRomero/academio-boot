package com.autentia.academioboot.service.storage;

import java.io.InputStream;
import java.nio.file.Path;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public interface StorageService {

    void init();

    void store(InputStream inputStream, String filename);

    Path load(String filename);

    Resource loadAsResource(String filename);

}