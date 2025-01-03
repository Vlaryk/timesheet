package com.example.timesheet.client;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
public class EmployeeResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private Integer salary;

}
