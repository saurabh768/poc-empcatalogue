package com.emp.EmployeeAddressManager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class EmployeeAddressManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeAddressManagerApplication.class, args);
	}

}
