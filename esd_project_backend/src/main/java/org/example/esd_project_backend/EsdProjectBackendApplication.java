package org.example.esd_project_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class EsdProjectBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(EsdProjectBackendApplication.class, args);
	}

}
