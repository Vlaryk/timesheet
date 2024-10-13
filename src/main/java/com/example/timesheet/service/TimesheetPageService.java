package com.example.timesheet.service;

import com.example.timesheet.controller.TimesheetPageDto;
import com.example.timesheet.model.Project;
import com.example.timesheet.model.Timesheet;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TimesheetPageService {
    private final TimesheetService timesheetService;
    private final ProjectService projectService;

    private TimesheetPageDto convert(Timesheet timesheet) {
        Project project = projectService.getById(timesheet.getProjectId())
                .orElseThrow();
        TimesheetPageDto timesheetPageDto = new TimesheetPageDto();
        timesheetPageDto.setProjectName(project.getName());
        timesheetPageDto.setTimesheetId(String.valueOf(timesheet.getId()));
        timesheetPageDto.setTimesheetMinutes(String.valueOf(timesheet.getMinutes()));
        timesheetPageDto.setTimesheetCreatedAt(String.valueOf(timesheet.getCreatedAt()));
        timesheetPageDto.setProjectId(String.valueOf(project.getId()));

        return timesheetPageDto;
    }

    public Optional<TimesheetPageDto> getById(Long id) {
        return timesheetService.getById(id).map(this::convert);
    }

    public List<TimesheetPageDto> getAll () {
        return timesheetService.getAll().stream().map(this::convert).toList();
    }

}
