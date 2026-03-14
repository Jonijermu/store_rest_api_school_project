package com.store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class StoreRestApiSchoolProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(StoreRestApiSchoolProjectApplication.class, args);
        System.out.print("Running on port http://localhost:8080/api");
	}

}
