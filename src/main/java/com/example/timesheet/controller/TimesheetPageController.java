package com.example.timesheet.controller;

import com.example.timesheet.model.Project;
import com.example.timesheet.model.Timesheet;
import com.example.timesheet.service.ProjectService;
import com.example.timesheet.service.TimesheetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.NoSuchElementException;
import java.util.Optional;

@Controller
@RequestMapping("/home/timesheets")
@RequiredArgsConstructor
public class TimesheetPageController {

    private final TimesheetService timesheetService;
    private final ProjectService projectService;

    @GetMapping("/{id}")
    public String getTimesheetPage(@PathVariable Long id, Model model) {
        Optional<Timesheet> timesheetOpt = timesheetService.getById(id);
        if (timesheetOpt.isEmpty()) {
            // FIXME вернуть страницу not found
            throw new NoSuchElementException();
        }

        Timesheet timesheet = timesheetOpt.get();
        Project project = projectService.getById(timesheet.getProjectId())
                .orElseThrow();


        TimesheetPageDto timesheetPageDto = new TimesheetPageDto();
        timesheetPageDto.setProjectName(project.getName());
        timesheetPageDto.setTimesheetId(String.valueOf(timesheet.getId()));
        timesheetPageDto.setTimesheetMinutes(String.valueOf(timesheet.getMinutes()));
        timesheetPageDto.setTimesheetCreatedAt(String.valueOf(timesheet.getCreatedAt()));

        model.addAttribute("timesheet",timesheetPageDto);
        return "timesheet-page.html";
    }

}
