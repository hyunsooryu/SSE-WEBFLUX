package me.burnie.ssewebflux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SseWebfluxApplication {

	public static void main(String[] args) {
		var app = new SpringApplication(SseWebfluxApplication.class);
		app.setWebApplicationType(WebApplicationType.REACTIVE);
		app.run(args);
	}

}
