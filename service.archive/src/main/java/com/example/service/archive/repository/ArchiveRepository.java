package com.example.service.archive.repository;

import com.example.service.archive.entity.Archive;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

@Repository
public class ArchiveRepository {
    private final JdbcTemplate jdbcTemplate;

    public ArchiveRepository(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Archive get(long id) {
        return jdbcTemplate.queryForObject("SELECT * FROM archive WHERE archive_id=?",
                new BeanPropertyRowMapper<>(Archive.class), id);
    }

    public Long getArchiveIdByRegistrationId(long registrationId) {
        return jdbcTemplate.queryForObject("SELECT archive_id FROM archive WHERE registration_id=?",
                new Object[]{registrationId}, Long.class);
    }

    public Long getRegistrationIdByArchiveId(long archiveId) {
        return jdbcTemplate.queryForObject("SELECT registration_id FROM archive WHERE archive_id=?",
                new Object[]{archiveId}, Long.class);
    }

    public void post(Archive archive) {
        jdbcTemplate.update("INSERT INTO archive (registration_id,mark,date) VALUES (?,?,?)",
                archive.getRegistration().getRegistrationId(), archive.getMark(), archive.getDate());
    }

    public void putMarkAndDate(long archiveId, int mark, Timestamp date) {
        jdbcTemplate.update("UPDATE archive SET mark=?, date=? WHERE archive_id=?", mark, date, archiveId);
    }

    public void delete(long id) {
        jdbcTemplate.update("DELETE FROM archive WHERE archive_id=?", id);
    }
}
