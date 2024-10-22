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
public class TimesheetApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(TimesheetApplication.class, args);
		TimesheetRepository timesheetRepo = ctx.getBean(TimesheetRepository.class);
		ProjectRepository projectRepo= ctx.getBean(ProjectRepository.class);
		EmployeeRepository employeeRepo = ctx.getBean(EmployeeRepository.class);
		UserRepository userRepo = ctx.getBean(UserRepository.class);

		User admin = new User();
		admin.setLogin("admin");
		admin.setPassword("$2a$12$9bhvkEze5zuiRxke8xIF4eANgdeeM5LoM8B2XZIMUo5wi2ufB1v8C"); //admin

		User user = new User();
		user.setLogin("user");
		user.setPassword("$2a$12$UyHLpDkGukhUhavdJdke6.isqWWuBeJWx2xrcIlGaXtiYaSg0IdmO"); //user


		admin = userRepo.save(admin);
		user = userRepo.save(user);

		UserRoleRepository userRoleRepository = ctx.getBean(UserRoleRepository.class);
		UserRole adminAdminRole = new UserRole();
		adminAdminRole.setUserId(admin.getId());
		adminAdminRole.setRoleName(Role.ADMIN.getName());
		userRoleRepository.save(adminAdminRole);

		UserRole adminUserRole = new UserRole();
		adminUserRole.setUserId(admin.getId());
		adminUserRole.setRoleName(Role.USER.getName());
		userRoleRepository.save(adminUserRole);

		UserRole userUserRole = new UserRole();
		userUserRole.setUserId(user.getId());
		userUserRole.setRoleName(Role.USER.getName());
		userRoleRepository.save(userUserRole);


		
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
