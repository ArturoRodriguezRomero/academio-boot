package com.autentia.academioboot.controller;

import java.util.List;

import com.autentia.academioboot.exception.ResourceNotFoundException;
import com.autentia.academioboot.model.Teacher;
import com.autentia.academioboot.service.teacher.TeacherService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

    private TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping("/all")
    public List<Teacher> getAllTeachers() {
        return teacherService.getAll();
    }

    @GetMapping("/{id}")
    public Teacher getTeacherById(@PathVariable(value = "id") int teacherId) {
        return teacherService.getById(teacherId)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher", "id", teacherId));
    }
}