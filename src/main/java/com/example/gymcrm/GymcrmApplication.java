package com.example.gymcrm;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.gymcrm.model.User;
import com.example.gymcrm.services.implementations.UserServiceImpl;

@SpringBootApplication
public class GymcrmApplication {

	public static void main(String[] args) {
		SpringApplication.run(GymcrmApplication.class, args);
	}

	@Bean
	public CommandLineRunner initData(UserServiceImpl userServiceImpl) {
		return args -> {

			userServiceImpl.eraseAll();

			User user1 = new User(null, "Yessica", "Apolinar", "laYess", "123", true);
			userServiceImpl.createUser(user1);

			User user2 = new User(null, "Bryan", "Hernandez", "ItsBrez", "123", true);
			userServiceImpl.createUser(user2);
		};
	}
}
