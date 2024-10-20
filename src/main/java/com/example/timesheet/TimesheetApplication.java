package com.example.timesheet;

import com.example.timesheet.model.Employee;
import com.example.timesheet.model.Project;
import com.example.timesheet.model.Timesheet;
import com.example.timesheet.repository.EmployeeRepository;
import com.example.timesheet.repository.ProjectRepository;
import com.example.timesheet.repository.TimesheetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

@SpringBootApplication
@RequiredArgsConstructor
public class TimesheetApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(TimesheetApplication.class, args);
		TimesheetRepository timesheetRepo = ctx.getBean(TimesheetRepository.class);
		ProjectRepository projectRepo= ctx.getBean(ProjectRepository.class);
		EmployeeRepository employeeRepo = ctx.getBean(EmployeeRepository.class);

		for (int i = 1; i <= 5 ; i++) {
			Project project = new Project();
			project.setName("Project # " + i);
			projectRepo.save(project);
		}

		for (int i = 1; i <= 10; i++) {
			Employee employee = new Employee();
			employee.setFirstName("Иван" + i);
			employee.setLastName("Петров");
			employee.setSalary(111);

			employeeRepo.save(employee);

		}

		LocalDate createdAt = LocalDate.now();
		for (int i = 1; i <= 10; i++) {
			createdAt = createdAt.plusDays(1);
			Timesheet timesheet = new Timesheet();
			timesheet.setProjectId(ThreadLocalRandom.current().nextLong(1,6));
			timesheet.setEmployeeId(ThreadLocalRandom.current().nextLong(1,11));
			timesheet.setCreatedAt(createdAt);
			timesheet.setMinutes(ThreadLocalRandom.current().nextInt(100,1000));

			timesheetRepo.save(timesheet);
		}
	}

}
