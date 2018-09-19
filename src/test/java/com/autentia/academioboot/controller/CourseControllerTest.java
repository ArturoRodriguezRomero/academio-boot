package com.autentia.academioboot.controller;

import com.autentia.academioboot.model.Course;
import com.autentia.academioboot.service.course.CourseService;
import com.autentia.academioboot.service.storage.StorageService;

import org.junit.Test;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CourseControllerTest {

    private CourseService courseService = mock(CourseService.class);
    private StorageService storageService = mock(StorageService.class);

    private CourseController sut = new CourseController(courseService, storageService);

    @Test
    public void whenCourseControllerGetAllShouldReturnCourseList() throws Exception {
        List<Course> expected = Arrays.asList(new Course(1, true, "test", 150, 1, 1, "test"),
                new Course(2, true, "test", 150, 2, 2, "test"));
        when(courseService.getAll()).thenReturn(expected);

        List<Course> result = sut.getAllCourse();

        assertThat(result, is(expected));
    }

    @Test
    public void whenCourseControllerGetByIdShouldReturnCourse() {
        Optional<Course> expected = Optional.of(new Course(1, true, "test", 150, 1, 1, "test"));
        when(courseService.getById(anyInt())).thenReturn(expected);

        Optional<Course> result = sut.getCourseById(expected.get().getId());

        assertThat(result, is(expected));
    }

    @Test
    public void whenCourseControllerCreateCourseShouldCreateCourse() {
        Course expected = new Course(1, true, "test", 150, 1, 1, "test");
        when(courseService.insert(expected)).thenReturn(expected);

        Course result = sut.createCourse(expected);

        assertThat(result, is(expected));
    }

    @Test
    public void whenServeFileShouldCallStorageServiceLoadAsResource() {
        String filename = "test";
        Resource resource = mock(Resource.class);
        when(resource.getFilename()).thenReturn(filename);
        when(storageService.loadAsResource(filename)).thenReturn(resource);

        ResponseEntity<Resource> result = sut.serveFile(filename);

        verify(storageService).loadAsResource(filename);
        assertThat(result.getStatusCode(), is(HttpStatus.OK));
        assertThat(result.getBody(), is(resource));
    }

    @Test
    public void whenHandleFileUploadShouldCallStorageServiceStoreAndRedirect() throws IOException {
        MultipartFile multipartFile = mock(MultipartFile.class);
        RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
        InputStream inputStream = mock(InputStream.class);
        String filename = "test";
        when(multipartFile.getInputStream()).thenReturn(inputStream);
        when(multipartFile.getOriginalFilename()).thenReturn(filename);

        String result = sut.handleFileUpload(multipartFile, redirectAttributes);

        verify(storageService).store(inputStream, filename);
        assertThat(result, is("redirect:/"));
    }
}