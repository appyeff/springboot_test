package org.demo.test.spingboot.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
public class SpringbootTestApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringbootTestApplication.class, args);
	}

}
