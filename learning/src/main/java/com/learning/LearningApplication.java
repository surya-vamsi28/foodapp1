package com.learning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.learning.service.UserService;

@SpringBootApplication
public class LearningApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext =  SpringApplication.run(LearningApplication.class, args);
//		RegisterService registerservice = applicationContext.getBean(RegisterService.class);
		
		
		
//		applicationContext.close();
	}

}
