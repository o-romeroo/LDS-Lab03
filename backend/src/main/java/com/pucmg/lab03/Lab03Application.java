package com.pucmg.lab03;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.pucmg.lab03")
public class Lab03Application {

	public static void main(String[] args) {
		SpringApplication.run(Lab03Application.class, args);
	}

}

