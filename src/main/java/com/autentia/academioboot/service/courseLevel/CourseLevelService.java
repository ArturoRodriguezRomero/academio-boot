package com.autentia.academioboot.service.courseLevel;

import java.util.List;
import java.util.Optional;
import com.autentia.academioboot.model.CourseLevel;
import org.springframework.stereotype.Service;

@Service
public interface CourseLevelService {
    List<CourseLevel> getAll();

    Optional<CourseLevel> getById(int id);
}