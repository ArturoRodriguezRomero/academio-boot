package com.autentia.academioboot.repository;

import com.autentia.academioboot.model.CourseLevel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseLevelRepository extends JpaRepository<CourseLevel, Integer> {

}