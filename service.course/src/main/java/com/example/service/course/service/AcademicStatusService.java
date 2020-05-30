package com.example.service.course.service;

import com.example.service.course.entity.AcademicStatus;
import com.example.service.course.repository.AcademicStatusRepository;
import org.springframework.stereotype.Service;

@Service
public class AcademicStatusService {
    private final AcademicStatusRepository academicStatusRepository;

    public AcademicStatusService(AcademicStatusRepository academicStatusRepository) {
        this.academicStatusRepository = academicStatusRepository;
    }

    public AcademicStatus get(long id) {
        return academicStatusRepository.get(id);
    }
}
