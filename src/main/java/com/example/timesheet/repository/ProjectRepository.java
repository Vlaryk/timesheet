package com.example.timesheet.repository;

import com.example.timesheet.model.Project;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;


@Repository
public class ProjectRepository {
    private Long id = 1L;
    private final List<Project> projects = new ArrayList<>();


    public Optional<Project> getById(Long id) {
        return projects.stream()
                .filter(project -> project.getId().equals(id)).findFirst();
    }

    public List<Project> getAll() {
        return List.copyOf(projects);
    }

    public Project create(Project project) {
        project.setId(id++);
        projects.add(project);
        return project;
    }

    public void delete (Long id) {
        projects.removeIf(project -> project.getId().equals(id));
    }
}
