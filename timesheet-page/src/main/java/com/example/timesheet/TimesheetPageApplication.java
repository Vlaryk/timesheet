package com.example.timesheet;

import com.example.timesheet.model.*;
import com.example.timesheet.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@SpringBootApplication
@RequiredArgsConstructor
public class TimesheetPageApplication {

	public static void main(String[] args) {
		SpringApplication.run(TimesheetPageApplication.class, args);
	}

}
