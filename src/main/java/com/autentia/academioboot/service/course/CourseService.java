package com.autentia.academioboot.service.course;

import java.util.List;
import java.util.Optional;

import com.autentia.academioboot.model.Course;
import org.springframework.stereotype.Service;

@Service
public interface CourseService {
    List<Course> getAll();

    Optional<Course> getById(int id);

    Course insert(Course course);
}