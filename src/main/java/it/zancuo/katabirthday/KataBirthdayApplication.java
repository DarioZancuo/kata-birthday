package it.zancuo.katabirthday;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class KataBirthdayApplication {

	public static void main(String[] args) {
		SpringApplication.run(KataBirthdayApplication.class, args);
	}

}
