package com.example.timesheet.service;

import com.example.timesheet.aspect.Timer;
import com.example.timesheet.model.Timesheet;
import com.example.timesheet.repository.TimesheetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.*;

@Component
@RequiredArgsConstructor
@Timer
public class TimesheetService {

    private final ProjectService projectService;

    private final TimesheetRepository repository;

    public List<Timesheet> findTimesheetsByEmployeeId(Long id) {
        return repository.findTimesheetsByEmployeeId(id);
    }

    public List<Timesheet> findTimesheetsByProjectId (Long id) {
        if (projectService.getById(id).isEmpty()) {
            throw new NoSuchElementException("Project with id " + id + "does not exist");
        }

        return repository.findByProjectId(id);
    }

    public List<Timesheet> findByCreatedAtBetween(LocalDate min, LocalDate max) {
        return repository.findByCreatedAtBetween(min, max);
    }

//    @Timer
    public Optional<Timesheet> findById(Long id) {
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

//    @Timer(enabled = false)
    public void delete (Long id) {
        repository.deleteById(id);
    }
}
