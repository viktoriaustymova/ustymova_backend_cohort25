package de.ait.tp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class TemplateProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(TemplateProjectApplication.class, args);
	}

}
