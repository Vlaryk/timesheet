package com.example.timesheet.controller;

import lombok.Data;

@Data
public class TimesheetPageDto {

    private String projectName;
    private String timesheetId;
    private String employeeId;
    private String timesheetMinutes;
    private String timesheetCreatedAt;
    private String projectId;
}
