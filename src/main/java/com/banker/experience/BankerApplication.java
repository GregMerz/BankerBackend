package com.banker.experience;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class BankerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankerApplication.class, args);
	}

}
