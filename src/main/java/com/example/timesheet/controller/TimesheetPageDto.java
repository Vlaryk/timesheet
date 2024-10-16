package com.example.timesheet.controller;

import lombok.Data;

import javax.naming.Name;
import java.time.LocalDate;

@Data
public class TimesheetPageDto {

    private String projectName;
    private String timesheetId;
    private String employeeId;
    private String timesheetMinutes;
    private String timesheetCreatedAt;
    private String projectId;
}
