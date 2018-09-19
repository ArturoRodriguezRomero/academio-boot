package com.autentia.academioboot.controller;

import java.util.List;

import com.autentia.academioboot.exception.ResourceNotFoundException;
import com.autentia.academioboot.model.CourseLevel;
import com.autentia.academioboot.service.courseLevel.CourseLevelService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/courseLevels")
public class CourseLevelController {

    CourseLevelService courseLevelService;

    @Autowired
    public CourseLevelController(CourseLevelService courseLevelService) {
        this.courseLevelService = courseLevelService;
    }

    @GetMapping("/all")
    public List<CourseLevel> getAllCourseLevels() {
        return courseLevelService.getAll();
    }

    @GetMapping("/{id}")
    public CourseLevel getCourseLevelById(@PathVariable(value = "id") int courseLevelId) {
        return courseLevelService.getById(courseLevelId)
                .orElseThrow(() -> new ResourceNotFoundException("CourseLevel", "id", courseLevelId));
    }
}