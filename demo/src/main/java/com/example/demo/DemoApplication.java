package com.example.demo;

import com.example.demo.dto.EmployeeDTO;
import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@EnableBatchProcessing
@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}


	@Bean
	public CommandLineRunner setupDevelopmentData(EmployeeService employeeService) {
		return args -> {
			EmployeeDTO employeeDTO = new EmployeeDTO();
			employeeDTO.setName("ADMIN");
			employeeDTO.setAge(99);
			employeeDTO.setFrontend(false);
			employeeDTO.setEmail("ADMIN@ADMIN.COM");
			employeeDTO.setPassword("AdMIN@AdMIN99");
			employeeService.saveEmployee(employeeDTO, true);
		};
	}

}
