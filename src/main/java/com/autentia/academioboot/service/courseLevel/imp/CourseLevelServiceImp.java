package com.autentia.academioboot.service.courseLevel.imp;

import java.util.List;
import java.util.Optional;

import com.autentia.academioboot.model.CourseLevel;
import com.autentia.academioboot.repository.CourseLevelRepository;
import com.autentia.academioboot.service.courseLevel.CourseLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseLevelServiceImp implements CourseLevelService {

    private CourseLevelRepository courseLevelRepository;

    @Autowired
    public CourseLevelServiceImp(CourseLevelRepository courseLevelRepository) {
        this.courseLevelRepository = courseLevelRepository;
    }

    public List<CourseLevel> getAll() {
        return courseLevelRepository.findAll();
    }

    public Optional<CourseLevel> getById(int id) {
        return courseLevelRepository.findById(id);
    }

}