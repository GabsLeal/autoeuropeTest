package com.autoEuropeTest.autoEurope;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2

public class AutoEuropeApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutoEuropeApplication.class, args);
	}

}
