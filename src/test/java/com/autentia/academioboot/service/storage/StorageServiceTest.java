package com.autentia.academioboot.service.storage;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.autentia.academioboot.exception.StorageException;
import com.autentia.academioboot.service.storage.imp.StorageServiceImp;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class StorageServiceTest {

    private String root = "/Users/arturo.rodriguez/Downloads/academio-file-storage/";
    private StorageService sut = new StorageServiceImp(root);

    @Test
    public void whenServiceInitStorageRootShouldExists() {
        Path rootPath = Paths.get(root);

        sut.init();

        assertThat(Files.exists(rootPath), is(true));
    }

    @Test(expected = StorageException.class)
    public void whenServiceInitWithWrongRootShouldThrowStorageException() {
        this.sut = new StorageServiceImp("/Users/arturo.rodriguez/Downloads/ioexception-test/castana");

        sut.init();
    }

    @Test
    public void whenServiceStoreShouldCreateFile() throws IOException {
        String filename = "test.txt";
        Path filePath = Paths.get(root + filename);
        InputStream inputStream = new ByteArrayInputStream("test".getBytes());

        sut.store(inputStream, filename);

        assertThat(Files.exists(filePath), is(true));
        Files.delete(filePath);
        assertThat(Files.exists(filePath), is(false));
    }

    @Test(expected = StorageException.class)
    public void whenServiceStoreWithWrongRootShouldThrowStorageException() {
        this.sut = new StorageServiceImp("/Users/arturo.rodriguez/Downloads/ioexception-test/castana");

        String filename = "test.txt";
        InputStream inputStream = new ByteArrayInputStream("test".getBytes());

        sut.store(inputStream, filename);
    }

    @Test(expected = StorageException.class)
    public void whenStoreNotSecureFileShouldThrowStorageException() throws IOException, StorageException {
        String filename = "../test.txt";
        InputStream inputStream = new ByteArrayInputStream("test".getBytes());

        sut.store(inputStream, filename);
    }

    @Test
    public void whenLoadShouldReturnAPathOfFile() throws IOException {
        String filename = "test.txt";
        Path expected = Paths.get(root + filename);
        InputStream inputStream = new ByteArrayInputStream("test".getBytes());
        sut.store(inputStream, filename);

        Path result = sut.load(filename);

        assertThat(result, is(expected));
        Files.delete(expected);
    }

    @Test
    public void whenLoadAsResourceShouldReturnResourceOfFile() throws MalformedURLException {
        String filename = "test.txt";
        Path filePath = Paths.get(root + filename);
        InputStream inputStream = new ByteArrayInputStream("test".getBytes());
        Resource expected = new UrlResource(filePath.toUri());
        sut.store(inputStream, filename);

        Resource result = sut.loadAsResource(filename);

        assertThat(result, is(expected));
    }

    @Test(expected = StorageException.class)
    public void whenLoadAsResourceAndResourceDoesntExists() throws IOException, StorageException {
        String filename = "..asdasd/sadads7/sadasd,c.a.-as-º1º12º__¨ÇÇ*^/esasdasdasdasdasd.txt";
        InputStream inputStream = new ByteArrayInputStream("test".getBytes());

        sut.store(inputStream, filename);
    }

    @Test(expected = StorageException.class)
    public void whenLoadAsResourceAndResourceIsNotReadable() throws IOException, StorageException {
        String filename = "..asdasd/sadads7/sadasd,c.a.-as-º1º12º__¨ÇÇ*^/esasdasdasdasdasd.txt";

        sut.loadAsResource(filename);
    }

    @Ignore
    @Test(expected = MalformedURLException.class)
    public void whenLoadAsResourceAndURLIsMalformed() throws IOException, StorageException {
        String filename = "///:::///:::::///";

        sut.loadAsResource(filename);
    }

}