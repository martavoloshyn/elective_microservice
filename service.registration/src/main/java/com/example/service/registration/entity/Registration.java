package com.example.service.registration.entity;

import lombok.Data;

@Data
public class Registration {
    private long registrationId;
    private Student student;
    private Course course;
    private int year;
    private int semester;
}
