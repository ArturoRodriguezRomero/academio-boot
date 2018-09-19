package com.autentia.academioboot.controller;

import com.autentia.academioboot.model.Teacher;
import com.autentia.academioboot.service.teacher.TeacherService;
import org.junit.Test;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class TeacherControllerTest {

    private TeacherService service = mock(TeacherService.class);

    private TeacherController sut = new TeacherController(service);

    @Test
    public void whenTeacherControllerGetAllShouldReturnTeacherList() {
        List<Teacher> teachers = Arrays.asList(new Teacher(1, "1"), new Teacher(2, "2"));
        when(service.getAll()).thenReturn(teachers);

        List<Teacher> result = sut.getAllTeachers();

        assertThat(result, is(teachers));
    }

    @Test
    public void whenTeacherControllerGetByIdShouldReturnTeacher() {
        Teacher expected = new Teacher(1, "test");
        when(service.getById(anyInt())).thenReturn(Optional.of(expected));

        Teacher result = sut.getTeacherById(expected.getId());

        assertThat(result, is(expected));
    }
}