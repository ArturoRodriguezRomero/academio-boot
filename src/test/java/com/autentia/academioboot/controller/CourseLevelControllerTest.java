package com.autentia.academioboot.controller;

import com.autentia.academioboot.exception.ResourceNotFoundException;
import com.autentia.academioboot.model.CourseLevel;
import com.autentia.academioboot.service.courseLevel.CourseLevelService;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class CourseLevelControllerTest {

    private CourseLevelService service = mock(CourseLevelService.class);

    private CourseLevelController sut = new CourseLevelController(service);

    @Test
    public void whenCourseLevelControllerGetAllShouldReturnCourseLevelList() throws Exception {
        List<CourseLevel> courseLevels = Arrays.asList(new CourseLevel(1, "1"), new CourseLevel(2, "2"));
        when(service.getAll()).thenReturn(courseLevels);

        List<CourseLevel> result = sut.getAllCourseLevels();

        assertThat(result, is(courseLevels));

    }

    @Test(expected = ResourceNotFoundException.class)
    public void whenCourseLevelControllerGetByNotExistingIdShouldThrowResourceNotFound() {
        sut.getCourseLevelById(-1);
    }
}