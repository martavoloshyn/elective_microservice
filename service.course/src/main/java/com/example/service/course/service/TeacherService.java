package com.example.service.course.service;

import com.example.service.course.entity.Teacher;
import com.example.service.course.repository.TeacherRepository;
import org.springframework.stereotype.Service;

@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final AcademicStatusService academicStatusService;

    public TeacherService(TeacherRepository teacherRepository, AcademicStatusService academicStatusService) {
        this.teacherRepository = teacherRepository;
        this.academicStatusService = academicStatusService;
    }

    public Teacher get(long id) {
        Teacher teacher = teacherRepository.get(id);
        teacher.setAcademicStatus(academicStatusService.get(teacherRepository.getAcademicStatusIdByTeacherId(id)));
        return teacher;
    }
}
