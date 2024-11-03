package com.example.timesheet.service;

import com.example.timesheet.model.Project;
import com.example.timesheet.repository.ProjectRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest
class ProjectServiceTest {

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    ProjectService projectService;

    @Test
    void getByIdEmpty() {
        assertFalse(projectRepository.existsById(2L));

        assertTrue(projectService.getById(2L).isEmpty());
    }

    @Test
    void getByIdPresent() {
        Project project = new Project();
        project.setName("projectName");
        project = projectRepository.save(project);

        Optional<Project> actual = projectService.getById(project.getId());

        assertTrue(actual.isPresent());
        assertEquals(actual.get().getId(),project.getId());
        assertEquals(actual.get().getName(),project.getName());
    }
}