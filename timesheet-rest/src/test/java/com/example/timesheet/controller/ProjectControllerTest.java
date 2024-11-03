package com.example.timesheet.controller;

import com.example.timesheet.model.Project;
import com.example.timesheet.repository.ProjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProjectControllerTest {
    @Autowired
    ProjectRepository projectRepository;


    @LocalServerPort
    private int port;
    private RestClient restClient;
    @BeforeEach
     void beforeEach() {
         restClient = RestClient.create("http://localhost:" + port);
    }

    @Test
    void getByIdNotFound() {
        assertThrows(HttpClientErrorException.NotFound.class, () -> {
            restClient.get()
                    .uri("/projects/-2")
                    .retrieve()
                    .toBodilessEntity();
        });
    }

    @Test
    void getByIdAllOk() {
        Project project = new Project();
        project.setName("projectName");
        Project expected = projectRepository.save(project);

        ResponseEntity<Project> actual = restClient.get()
                .uri("/projects/" + expected.getId())
                .retrieve().toEntity(Project.class);

        assertEquals(HttpStatus.OK, actual.getStatusCode());
        Project responseBody = actual.getBody();
        assertNotNull(responseBody);
        assertEquals(project.getId(),responseBody.getId());
        assertEquals(project.getName(),responseBody.getName());
    }

    @Test
    void testCreate() {
        Project toCreate = new Project();
        toCreate.setName("newName");

        ResponseEntity<Project> actual = restClient.post()
                .uri("/projects")
                .body(toCreate)
                .retrieve()
                .toEntity(Project.class);

        assertEquals(HttpStatus.CREATED,actual.getStatusCode());
        assertNotNull(actual.getBody());
        assertNotNull(actual);
        assertEquals(actual.getBody().getName(), toCreate.getName());

        assertTrue(projectRepository.existsById(actual.getBody().getId()));
    }

    @Test
    void testDelete() {
        Project project = new Project();
        project.setName("newName");
        project = projectRepository.save(project);


        ResponseEntity<Void> response = restClient
                .delete()
                .uri("/projects/" + project.getId())
                .retrieve()
                .toBodilessEntity();

        assertEquals(HttpStatus.NO_CONTENT,response.getStatusCode());
        assertFalse(projectRepository.existsById(project.getId()));
    }
}