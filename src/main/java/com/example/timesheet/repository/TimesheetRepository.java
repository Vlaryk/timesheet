package com.example.timesheet.repository;

import com.example.timesheet.model.Timesheet;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class TimesheetRepository {
    private Long sequence = 1L;
    private final List<Timesheet> timesheets = new ArrayList<>();

    public Optional<Timesheet> getById(Long id) {
        return timesheets.stream()
                .filter(it -> Objects.equals(id, it.getId()))
                .findFirst();
    }

    public List<Timesheet>getAll() {
        return List.copyOf(timesheets);
    }

    public Timesheet create(Timesheet timesheet) {
        timesheet.setId(sequence++);
        timesheets.add(timesheet);
        timesheet.setCreatedAt(LocalDate.now());
        return timesheet;
    }

    public void delete (Long id) {
        timesheets.stream()
                .filter(ts -> Objects.equals(ts.getId(),id))
                .findFirst()
                .ifPresent(timesheets::remove);
    }
}
