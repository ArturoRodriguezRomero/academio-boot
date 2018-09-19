package com.autentia.academioboot.service.teacher;

import java.util.List;
import java.util.Optional;

import com.autentia.academioboot.model.Teacher;
import org.springframework.stereotype.Service;

@Service
public interface TeacherService {
    List<Teacher> getAll();

    Optional<Teacher> getById(int id);
}