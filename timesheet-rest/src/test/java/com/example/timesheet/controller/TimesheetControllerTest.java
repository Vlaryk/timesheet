package com.example.timesheet.controller;

import com.example.timesheet.model.Project;
import com.example.timesheet.model.Timesheet;
import com.example.timesheet.repository.ProjectRepository;
import com.example.timesheet.repository.TimesheetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestClient;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TimesheetControllerTest {

    @Autowired
    TimesheetRepository timesheetRepository;

    @Autowired
    ProjectRepository projectRepository;

    @LocalServerPort
    private int port;

    private RestClient restClient;

    @BeforeEach
    void beforeEach() {
        restClient = RestClient.create("http://localhost:" + port);
        timesheetRepository.deleteAll();
    }

    @Test
    void testGetById() {
        Timesheet timesheet = new Timesheet();
        timesheet.setEmployeeId(1L);
        timesheet.setProjectId(1L);
        timesheet.setCreatedAt(LocalDate.now());
        timesheet.setMinutes(60);
        Timesheet expected = timesheetRepository.save(timesheet);

        ResponseEntity<Timesheet> actual = restClient.get()
                .uri("/timesheets/" + expected.getId())
                .retrieve().toEntity(Timesheet.class);

        assertEquals(HttpStatus.OK, actual.getStatusCode());
        Timesheet responseBody = actual.getBody();
        assertNotNull(responseBody);
        assertEquals(timesheet.getId(),responseBody.getId());
        assertEquals(timesheet.getMinutes(),responseBody.getMinutes());
        assertEquals(timesheet.getCreatedAt(),responseBody.getCreatedAt());
        assertEquals(timesheet.getProjectId(),responseBody.getProjectId());
        assertEquals(timesheet.getEmployeeId(),responseBody.getEmployeeId());
    }

    @Test
    void testGetAll() {
        Project project = new Project();
        project.setName("newName");
        project = projectRepository.save(project);

        List<Timesheet> timesheets = new ArrayList<>();
        for (int i = 1; i <= 5 ; i++) {
            Timesheet timesheet = new Timesheet();
            timesheet.setEmployeeId(1L);
            timesheet.setProjectId(project.getId());
            timesheet.setCreatedAt(LocalDate.now());
            timesheet.setMinutes(60);
            timesheets.add(timesheetRepository.save(timesheet));
        }

        ResponseEntity<List<Timesheet>> actual = restClient.get()
                .uri("/timesheets")
                .retrieve().toEntity(new ParameterizedTypeReference<List<Timesheet>>() {
                });

        List<Timesheet> response = actual.getBody();
        assertNotNull(response);
        assertEquals(HttpStatus.OK, actual.getStatusCode());
        assertEquals(5, response.size());
        for (int i = 0; i < response.size(); i++) {
            assertEquals(timesheets.get(i).getEmployeeId(),response.get(i).getEmployeeId());
            assertEquals(timesheets.get(i).getProjectId(),response.get(i).getProjectId());
            assertEquals(timesheets.get(i).getMinutes(),response.get(i).getMinutes());
            assertEquals(timesheets.get(i).getCreatedAt(),response.get(i).getCreatedAt());
        }


    }

    @Test
    void testCreate() {
        Project project = new Project();
        project.setName("newName");
        project = projectRepository.save(project);

        Timesheet toCreate = new Timesheet();
        toCreate.setEmployeeId(1L);
        toCreate.setProjectId(project.getId());
        toCreate.setCreatedAt(LocalDate.now());
        toCreate.setMinutes(60);

        ResponseEntity<Timesheet> actual = restClient.post()
                .uri("/timesheets")
                .body(toCreate)
                .retrieve()
                .toEntity(Timesheet.class);

        Timesheet responseBody = actual.getBody();

        assertEquals(HttpStatus.CREATED,actual.getStatusCode());
        assertNotNull(responseBody);
        assertEquals(toCreate.getMinutes(),responseBody.getMinutes());
        assertEquals(toCreate.getCreatedAt(),responseBody.getCreatedAt());
        assertEquals(toCreate.getProjectId(),responseBody.getProjectId());
        assertEquals(toCreate.getEmployeeId(),responseBody.getEmployeeId());

        assertTrue(timesheetRepository.existsById(responseBody.getId()));
    }

    @Test
    void testDelete() {
        Timesheet timesheet = new Timesheet();
        timesheet.setEmployeeId(1L);
        timesheet.setProjectId(1L);
        timesheet.setCreatedAt(LocalDate.now());
        timesheet.setMinutes(60);
        timesheet = timesheetRepository.save(timesheet);


        ResponseEntity<Void> response = restClient
                .delete()
                .uri("/timesheets/" + timesheet.getId())
                .retrieve()
                .toBodilessEntity();

        assertEquals(HttpStatus.NO_CONTENT,response.getStatusCode());
        assertFalse(timesheetRepository.existsById(timesheet.getId()));
    }
}