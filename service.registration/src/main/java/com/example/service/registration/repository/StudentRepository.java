package com.example.service.registration.repository;

import com.example.service.registration.entity.Student;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class StudentRepository {
    private final JdbcTemplate jdbcTemplate;

    public StudentRepository(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Student get(long id) {
        return jdbcTemplate.queryForObject("SELECT * FROM students WHERE student_id=?",
                new BeanPropertyRowMapper<>(Student.class), id);
    }

    public Long getGroupIdByStudentId(long studentId) {
        return jdbcTemplate.queryForObject("SELECT group_id FROM students WHERE student_id=?",
                new Object[]{studentId}, Long.class);
    }
}
