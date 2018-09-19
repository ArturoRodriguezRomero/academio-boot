package com.autentia.academioboot.service.teacher;

import static org.mockito.Mockito.*;

import com.autentia.academioboot.repository.TeacherRepository;
import com.autentia.academioboot.service.teacher.imp.TeacherServiceImp;

import org.junit.Test;

public class TeacherServiceTest {

    TeacherRepository teacherRepository = mock(TeacherRepository.class);
    TeacherService sut = new TeacherServiceImp(teacherRepository);

    @Test
    public void whenTeacherServiceGetAllShouldCallRepository() {
        sut.getAll();

        verify(teacherRepository).findAll();
    }

    @Test
    public void whenTeacherServiceGetByIdShouldCallRepository() {
        sut.getById(1);

        verify(teacherRepository).findById(1);
    }
}