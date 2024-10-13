package com.example.timesheet.service;

import com.example.timesheet.controller.ProjectPageDto;
import com.example.timesheet.model.Project;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ProjectPageService {
    private final ProjectService service;

    public ProjectPageDto getProjectById(Long id) {
        System.out.println(id);
        return convert(service.getById(id).orElseThrow());
    }

    public List<ProjectPageDto> getAllProjects() {
        return service.getAll().stream().map(this::convert).toList();
    }



    private ProjectPageDto convert(Project project) {
        ProjectPageDto projectPageDto = new ProjectPageDto();
        projectPageDto.setId(String.valueOf(project.getId()));
        projectPageDto.setName(project.getName());
        return projectPageDto;
    }
}
