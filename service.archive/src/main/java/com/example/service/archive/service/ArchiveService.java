package com.example.service.archive.service;

import com.example.service.archive.entity.Archive;
import com.example.service.archive.entity.Registration;
import com.example.service.archive.repository.ArchiveRepository;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;

@Service
public class ArchiveService {
    private final String REGISTRATION_URL = "http://localhost:8097/registration/";

    private final ArchiveRepository archiveRepository;

    public ArchiveService(ArchiveRepository archiveRepository) {
        this.archiveRepository = archiveRepository;
    }

    public Archive get(long id) {
        Archive archive = archiveRepository.get(id);
        ResponseEntity<Registration> response = new RestTemplate()
                .exchange(REGISTRATION_URL + archiveRepository.getRegistrationIdByArchiveId(id), HttpMethod.GET, null,
                        new ParameterizedTypeReference<Registration>() {});
        archive.setRegistration(response.getBody());
        return archive;
    }

    public Long post(Archive archive) {
        archiveRepository.post(archive);
        return archiveRepository.getArchiveIdByRegistrationId(archive.getRegistration().getRegistrationId());
    }

    public Archive putMarkAndDate(long archiveId, int mark, Timestamp date) {
        archiveRepository.putMarkAndDate(archiveId, mark, date);
        return get(archiveId);
    }

    public void delete(long id) {
        archiveRepository.delete(id);
    }
}
