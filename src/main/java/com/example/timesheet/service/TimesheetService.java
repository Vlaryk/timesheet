package com.example.timesheet.service;

import com.example.timesheet.model.Timesheet;
import com.example.timesheet.repository.TimesheetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.*;

@Component
@RequiredArgsConstructor
public class TimesheetService {

    private final ProjectService projectService;

    private final TimesheetRepository repository;

    public List<Timesheet> findTimesheetsByEmployeeId(Long id) {
        return repository.findTimesheetsByEmployeeId(id);
    }

    public Optional<Timesheet> getById(Long id) {
        return repository.findById(id);
    }

    public List<Timesheet> getAll() {
        return repository.findAll();
    }

    public List<Timesheet> getAll(LocalDate createdAtBefore,LocalDate createdAtAfter) {
        //FIXME вернуть фильтрацию
        return repository.findAll();
    }

    public Timesheet create(Timesheet timesheet) {
        if (projectService.getById(timesheet.getProjectId()).isEmpty()) {
            throw new NoSuchElementException();
        }
        return repository.save(timesheet);
    }

    public void delete (Long id) {
        repository.deleteById(id);
    }
}
