package com.autentia.academioboot.service.course.imp;

import java.util.List;
import java.util.Optional;

import com.autentia.academioboot.model.Course;
import com.autentia.academioboot.repository.CourseRepository;
import com.autentia.academioboot.service.course.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CourseServiceImp implements CourseService {

    private CourseRepository courseRepository;

    @Autowired
    public CourseServiceImp(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> getAll() {
        return courseRepository.findAll();
    }

    public Optional<Course> getById(int id) {
        return courseRepository.findById(id);
    }

    @Transactional
    public Course insert(Course course) {
        return courseRepository.saveAndFlush(course);
    }

}