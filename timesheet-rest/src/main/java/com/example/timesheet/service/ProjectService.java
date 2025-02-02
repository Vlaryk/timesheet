package com.example.timesheet.service;

import com.example.aspect.logging.Logging;
import com.example.timesheet.model.Project;
import com.example.timesheet.model.Timesheet;
import com.example.timesheet.repository.ProjectRepository;
import com.example.timesheet.repository.TimesheetRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Data
@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final TimesheetRepository timesheetRepository;

    @Logging
    public Optional<Project> getById(Long id) {
        return projectRepository.findById(id);
    }


    public List<Project> getAll() {
        return projectRepository.findAll();
    }


    public Project create(Project project) {
        return projectRepository.save(project);
    }

    public void delete (Long id) {
        projectRepository.deleteById(id);
    }
}
