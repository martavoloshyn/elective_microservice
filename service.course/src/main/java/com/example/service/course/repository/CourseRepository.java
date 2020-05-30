package com.example.service.course.repository;

import com.example.service.course.entity.Course;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class CourseRepository {

    private final JdbcTemplate jdbcTemplate;

    public CourseRepository(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Course get(long id) {
        return jdbcTemplate.queryForObject("SELECT * FROM courses WHERE course_id=?",
                new BeanPropertyRowMapper<>(Course.class), id);
    }

    public Long getTeacherIdByCourseId(long courseId) {
        return jdbcTemplate.queryForObject("SELECT teacher_id FROM courses WHERE course_id=?",
                new Object[]{courseId}, Long.class);
    }

    public void putTeacher(long courseId, long teacherId) {
        jdbcTemplate.update("UPDATE courses SET teacher_id=? WHERE course_id=?", teacherId, courseId);
    }
}
