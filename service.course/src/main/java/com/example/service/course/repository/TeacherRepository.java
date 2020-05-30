package com.example.service.course.repository;

import com.example.service.course.entity.Teacher;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TeacherRepository {
    private final JdbcTemplate jdbcTemplate;

    public TeacherRepository(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Teacher get(long id) {
        return jdbcTemplate.queryForObject("SELECT * FROM teachers WHERE teacher_id=?",
                new BeanPropertyRowMapper<>(Teacher.class), id);
    }

    public Long getAcademicStatusIdByTeacherId(long teacherId) {
        return jdbcTemplate.queryForObject("SELECT academic_status_id FROM teachers WHERE teacher_id=?",
                new Object[]{teacherId}, Long.class);
    }
}
