package com.example.timesheet.controller;

import com.example.timesheet.model.Employee;
import com.example.timesheet.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController()
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService service;

    @GetMapping
    public ResponseEntity<List<Employee>> findAllEmployees() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> findEmployeePage(@PathVariable Long id) {
        Optional<Employee> employeeOpt = service.findById(id);
        if (employeeOpt.isEmpty()) {
            throw new NoSuchElementException();
        }
        return ResponseEntity.ok(employeeOpt.get());
    }

    @PostMapping
    public ResponseEntity<Employee> create(@RequestBody Employee employee) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(employee));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete (@PathVariable Long id) {
        service.delete(id);

        return ResponseEntity.notFound().build();
    }
}
