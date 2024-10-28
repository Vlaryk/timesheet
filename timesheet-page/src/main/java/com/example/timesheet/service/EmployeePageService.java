package com.example.timesheet.service;
import com.example.timesheet.Page.EmployeePageDto;
import com.example.timesheet.model.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeePageService {
    private final EmployeeService service;

    public Optional<EmployeePageDto> findById (Long id) {
        return service.findById(id).map(this::convert);
    }

    public List<EmployeePageDto> findAll () {
        return service.findAll().stream().map(this::convert).toList();
    }
    private EmployeePageDto convert(Employee employee) {
        EmployeePageDto employeePageDto = new EmployeePageDto();
        employeePageDto.setId(String.valueOf(employee.getId()));
        employeePageDto.setFirstName(employee.getFirstName());
        employeePageDto.setLastName(employee.getLastName());
        employeePageDto.setSalary(String.valueOf(employee.getSalary()));

        return employeePageDto;
    }


}
