package com.acts.moneymanger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class MoneymangerApplication {

	public static void main(String[] args) {

		SpringApplication.run(MoneymangerApplication.class, args);
	}

}
