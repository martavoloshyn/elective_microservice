package com.example.service.archive.entity;

import lombok.Data;

@Data
public class Student {
    private long studentId;
    private String name;
    private String surname;
    private String middleName;
    private Group group;
    private int yearOfEntry;
}
