package dev.ansuro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PizzaboyApplication {

	public static void main(String[] args) {
            SpringApplication springApplication = new SpringApplication(PizzaboyApplication.class);
            // set dev-profile
            springApplication.setAdditionalProfiles("dev");
            springApplication.run(args);
	}
}
