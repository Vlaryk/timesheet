package com.example.timesheet.service;

import com.example.timesheet.model.Timesheet;
import com.example.timesheet.repository.TimesheetRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.*;

@Component
public class TimesheetService {

    private final ProjectService projectService;

    private final TimesheetRepository repository;

    public TimesheetService(TimesheetRepository repository,ProjectService projectService) {
        this.repository = repository;
        this.projectService = projectService;
    }

    public Optional<Timesheet> getById(Long id) {
        return repository.getById(id);
    }

    public List<Timesheet> getAll() {
        return repository.getAll();
    }

    public Timesheet create(Timesheet timesheet) {
        if (projectService.getById(timesheet.getProjectId()).isEmpty()) {
            return null;
        }
        return repository.create(timesheet);
    }

    public void delete (Long id) {
        repository.delete(id);
    }
}
