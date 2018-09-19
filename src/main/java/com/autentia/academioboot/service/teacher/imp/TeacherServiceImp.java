package com.autentia.academioboot.service.teacher.imp;

import java.util.List;
import java.util.Optional;

import com.autentia.academioboot.model.Teacher;
import com.autentia.academioboot.repository.TeacherRepository;
import com.autentia.academioboot.service.teacher.TeacherService;
import org.springframework.stereotype.Service;

@Service
public class TeacherServiceImp implements TeacherService {

    private TeacherRepository teacherRepository;

    public TeacherServiceImp(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public List<Teacher> getAll() {
        return teacherRepository.findAll();
    }

    public Optional<Teacher> getById(int id) {
        return teacherRepository.findById(id);
    }

}