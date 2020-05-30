package com.example.service.course.entity;

import lombok.Data;

@Data
public class Course {
    private long courseId;
    private String courseName;
    private Teacher teacher;
}
