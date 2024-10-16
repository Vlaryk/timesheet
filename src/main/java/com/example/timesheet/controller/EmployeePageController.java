package com.example.timesheet.controller;

import com.example.timesheet.service.EmployeePageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/home/employees")
public class EmployeePageController {
    private final EmployeePageService service;

    @GetMapping("/{id}")
    public String findEmployeePage(@PathVariable Long id, Model model) {
        Optional<EmployeePageDto> employeePageDto = service.findById(id);
        if (employeePageDto.isEmpty()) {
            return "not-found.html";
        }

        model.addAttribute("employee",employeePageDto.get());
        return "employee-page.html";
    }

//    @GetMapping
//    public String findAll(Model model) {
//        model.addAttribute("employees",service.findAll());
//        return "employee-page.html";
//    }

}
