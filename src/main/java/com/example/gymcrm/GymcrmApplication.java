package com.example.gymcrm;

import java.sql.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.gymcrm.model.Trainee;
import com.example.gymcrm.model.TrainingType;
import com.example.gymcrm.model.User;
import com.example.gymcrm.services.implementations.TraineeServiceImpl;
import com.example.gymcrm.services.implementations.TrainingTypeServiceImpl;
import com.example.gymcrm.services.implementations.UserServiceImpl;

@SpringBootApplication
public class GymcrmApplication {

	public static void main(String[] args) {
		SpringApplication.run(GymcrmApplication.class, args);
	}

	@Bean
	public CommandLineRunner initData(
			UserServiceImpl userServiceImpl,
			TrainingTypeServiceImpl trainingTypeServiceImpl,
			TraineeServiceImpl traineeServiceImpl) {
		return args -> {

			userServiceImpl.deleteAll();
			trainingTypeServiceImpl.deleteAll();
			traineeServiceImpl.deleteAll();

			User user1 = new User(null, "Yessica", "Apolinar", "laYess", "123", true);
			User user2 = new User(null, "Bryan", "Hernandez", "ItsBrez", "123", true);

			userServiceImpl.createUser(user1);
			userServiceImpl.createUser(user2);

			TrainingType trainingType1 = new TrainingType(null, "Intense");
			TrainingType trainingType2 = new TrainingType(null, "Easy");
			TrainingType trainingType3 = new TrainingType(null, "Medium");

			trainingTypeServiceImpl.createTrainingType(trainingType1);
			trainingTypeServiceImpl.createTrainingType(trainingType2);
			trainingTypeServiceImpl.createTrainingType(trainingType3);

			Trainee trainee1 = new Trainee(null, new Date(0), "Santo Tomás", null, null);
			trainee1.setUser(user1);
			traineeServiceImpl.createTrainee(trainee1);
		};
	}
}
