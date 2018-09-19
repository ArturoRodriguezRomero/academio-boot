package com.autentia.academioboot.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "courses")
@EntityListeners(AuditingEntityListener.class)
public class Course implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private boolean isActive;
    @NotNull

    @NotNull
    private String title;

    @NotNull
    private int hours;

    @NotNull
    private int courseLevel;

    @NotNull
    private int teacher;

    private String agendaFileName;

    public Course(int id, boolean isActive, String title, int hours, int courseLevel, int teacher,
            String agendaFileName) {
        this.id = id;
        this.isActive = isActive;
        this.title = title;
        this.hours = hours;
        this.courseLevel = courseLevel;
        this.teacher = teacher;
        this.agendaFileName = agendaFileName;
    }

    public Course() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean getIsActive() {
        return this.isActive;
    }

    public void isIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getHours() {
        return this.hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getCourseLevel() {
        return this.courseLevel;
    }

    public void setCourseLevel(int courseLevel) {
        this.courseLevel = courseLevel;
    }

    public int getTeacher() {
        return this.teacher;
    }

    public void setTeacher(int teacher) {
        this.teacher = teacher;
    }

    public String getAgendaFileName() {
        return this.agendaFileName;
    }

    public void setAgendaFileName(String agendaFileName) {
        this.agendaFileName = agendaFileName;
    }
}