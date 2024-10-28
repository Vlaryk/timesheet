package com.example.timesheet.service;

import com.example.timesheet.model.Employee;
import com.example.timesheet.model.Timesheet;
import com.example.timesheet.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository repository;

    public List<Employee> findAll() {
        return repository.findAll();
    }

    public Optional<Employee> findById(Long id) {
        return repository.findById(id);
    }

    public Employee create (Employee employee) {
        return repository.save(employee);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
