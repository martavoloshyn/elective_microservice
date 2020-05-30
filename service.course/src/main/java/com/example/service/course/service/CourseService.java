package com.example.service.course.service;

import com.example.service.course.entity.Course;
import com.example.service.course.repository.CourseRepository;
import org.springframework.stereotype.Service;

@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final TeacherService teacherService;

    public CourseService(final CourseRepository courseRepository, final TeacherService teacherService) {
        this.courseRepository = courseRepository;
        this.teacherService = teacherService;
    }

    public Course get(long id) {
        Course course = courseRepository.get(id);
        course.setTeacher(teacherService.get(courseRepository.getTeacherIdByCourseId(id)));
        return course;
    }

    public Course putTeacher(long courseId, long teacherId) {
        courseRepository.putTeacher(courseId, teacherId);
        return get(courseId);
    }
}
