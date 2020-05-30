package com.example.service.registration.service;


import com.example.service.registration.entity.Course;
import com.example.service.registration.entity.Registration;
import com.example.service.registration.repository.RegistrationRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class RegistrationService {

    private final String COURSE_URL = "http://localhost:8096/course/";

    private final RegistrationRepository registrationRepository;
    private final StudentService studentService;

    public RegistrationService(RegistrationRepository registrationRepository, StudentService studentService) {
        this.registrationRepository = registrationRepository;
        this.studentService = studentService;
    }

    public Registration get(long id) {
        Registration registration = registrationRepository.get(id);
        ResponseEntity<Course> response = new RestTemplate()
                .exchange(COURSE_URL + registrationRepository.getCourseIdByRegistrationId(id), HttpMethod.GET, null,
                        new ParameterizedTypeReference<Course>() {});
        registration.setCourse(response.getBody());
        registration.setStudent(studentService.get(registrationRepository.getStudentIdByRegistrationId(id)));
        return registration;
    }

    public Long post(Registration registration) {
        registrationRepository.post(registration);
        return registrationRepository.getRegistrationIdIdByStudentIdAndCourseId(
                registration.getStudent().getStudentId(),
                registration.getCourse().getCourseId());
    }

    public void delete(long id) {
        registrationRepository.delete(id);
    }
}
