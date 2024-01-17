package com.restapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@PropertySource("classpath:application.properties")
public class SpringBootRestApiStarterApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRestApiStarterApplication.class, args);
	}

}
