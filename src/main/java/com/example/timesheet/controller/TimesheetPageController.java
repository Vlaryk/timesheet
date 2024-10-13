package com.example.timesheet.controller;

import com.example.timesheet.model.Project;
import com.example.timesheet.model.Timesheet;
import com.example.timesheet.service.ProjectService;
import com.example.timesheet.service.TimesheetPageService;
import com.example.timesheet.service.TimesheetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Controller
@RequestMapping("/home/timesheets")
@RequiredArgsConstructor
public class TimesheetPageController {

    private final TimesheetPageService service;

    @GetMapping
    public String getAllTimesheets(Model model) {
        List<TimesheetPageDto> timesheets = service.getAll();
        model.addAttribute("timesheets",timesheets);
        return "timesheets-page.html";
    }

    @GetMapping("/{id}")
    public String getTimesheetPage(@PathVariable Long id, Model model) {
        Optional<TimesheetPageDto> timesheetOpt = service.getById(id);
        if (timesheetOpt.isEmpty()) {
            return "not-found.html";
        }
        model.addAttribute("timesheet",timesheetOpt.get());
        return "timesheet-page.html";
    }

}
