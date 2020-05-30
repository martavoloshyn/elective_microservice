package com.example.service.course.repository;

import com.example.service.course.entity.AcademicStatus;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AcademicStatusRepository {
    private final JdbcTemplate jdbcTemplate;

    public AcademicStatusRepository(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public AcademicStatus get(long id) {
        return jdbcTemplate.queryForObject("SELECT * FROM academic_statuses WHERE academic_status_id=?",
                new BeanPropertyRowMapper<>(AcademicStatus.class), id);
    }
}
