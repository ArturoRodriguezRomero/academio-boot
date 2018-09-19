package com.autentia.academioboot.service.course;

import com.autentia.academioboot.model.Course;
import com.autentia.academioboot.repository.CourseRepository;
import com.autentia.academioboot.service.course.imp.CourseServiceImp;

import org.junit.Test;

import static org.mockito.Mockito.*;

public class CourseServiceTest {

    private CourseRepository courseRepository = mock(CourseRepository.class);
    private CourseService sut = new CourseServiceImp(courseRepository);

    @Test
    public void whenCourseServiceGetAllShouldCallRepository() {
        sut.getAll();

        verify(courseRepository).findAll();
    }

    @Test
    public void whenCourseServiceGetByIdShouldCallRepository() {
        sut.getById(1);

        verify(courseRepository).findById(1);
    }

    @Test
    public void whenCourseServiceCreateNewShouldCallRepository() {
        Course expected = new Course(1, true, "test", 123, 1, 1, "test");

        sut.insert(expected);

        verify(courseRepository).saveAndFlush(expected);
    }
}