package com.example.timesheet.rest;


import com.example.timesheet.model.Timesheet;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.timesheet.service.TimesheetService;

import java.util.List;
import java.util.Optional;

@RestController
public class TimesheetController {


    private final TimesheetService service;

    public TimesheetController(TimesheetService service) {
        this.service = service;
    }

    @GetMapping("employees/{id}/timesheets")
    public ResponseEntity<List<Timesheet>> findTimesheetsByEmployeeId(@PathVariable Long id) {

        return ResponseEntity.ok(service.findTimesheetsByEmployeeId(id));
    }

    @GetMapping("/timesheets/{id}")
    public ResponseEntity<Timesheet> get(@PathVariable Long id) {
        Optional<Timesheet> ts = service.getById(id);
        if (ts.isPresent()) {
            return ResponseEntity.ok().body(ts.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/timesheets")
    public ResponseEntity<List<Timesheet>>getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @PostMapping("/timesheets")
    public ResponseEntity<Timesheet> create(@RequestBody Timesheet timesheet) {
        timesheet = service.create(timesheet);
        if (timesheet == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(timesheet);
    }

    @DeleteMapping("/timesheets/{id}")
    public ResponseEntity<Void> delete (@PathVariable Long id) {
        service.delete(id);
        //204 no content
        return ResponseEntity.noContent().build();
    }
}
