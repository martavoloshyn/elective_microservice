package com.example.service.registration.entity;

import lombok.Data;

@Data
public class Teacher {
    private long teacherId;
    private String name;
    private String surname;
    private String middleName;
    private AcademicStatus academicStatus;
}
