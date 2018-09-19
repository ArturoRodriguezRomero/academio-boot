package com.autentia.academioboot.service.courseLevel;

import static org.mockito.Mockito.*;

import com.autentia.academioboot.repository.CourseLevelRepository;
import com.autentia.academioboot.service.courseLevel.imp.CourseLevelServiceImp;

import org.junit.Test;

public class CourseLevelServiceTest {

    CourseLevelRepository courseLevelRepository = mock(CourseLevelRepository.class);
    CourseLevelService sut = new CourseLevelServiceImp(courseLevelRepository);

    @Test
    public void whenCourseLevelServiceGetAllShouldCallRepository() {
        sut.getAll();

        verify(courseLevelRepository).findAll();
    }

    @Test
    public void whenCourseLevelServiceGetByIdShouldCallRepository() {
        sut.getById(1);

        verify(courseLevelRepository).findById(1);
    }

}