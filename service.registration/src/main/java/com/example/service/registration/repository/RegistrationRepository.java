package com.example.service.registration.repository;

import com.example.service.registration.entity.Registration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RegistrationRepository {

    private final JdbcTemplate jdbcTemplate;

    public RegistrationRepository(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Registration get(long id) {
        return jdbcTemplate.queryForObject("SELECT * from registrations WHERE registration_id=?",
                new BeanPropertyRowMapper<>(Registration.class), id);
    }

    public Long getCourseIdByRegistrationId(long registrationId) {
        return jdbcTemplate.queryForObject("SELECT course_id FROM registrations WHERE registration_id=?",
                new Object[]{registrationId}, Long.class);
    }

    public Long getRegistrationIdIdByStudentIdAndCourseId(long studentId, long courseId){
        return jdbcTemplate.queryForObject(
                "SELECT registration_id FROM registrations WHERE student_id=? and course_id=?",
                new Object[]{studentId, courseId},
                Long.class);
    }

    public Long getStudentIdByRegistrationId(long registrationId) {
        return jdbcTemplate.queryForObject("SELECT student_id FROM registrations WHERE registration_id=?",
                new Object[]{registrationId}, Long.class);
    }

    public void post(Registration registration) {
        jdbcTemplate.update("INSERT INTO registrations (student_id,course_id,year,semester) VALUES (?,?,?,?)",
                registration.getStudent().getStudentId(), registration.getCourse().getCourseId(),
                registration.getYear(), registration.getSemester());
    }

    public void delete(long id) {
        jdbcTemplate.update("DELETE FROM registrations WHERE registration_id=?", id);
    }
}
