package com.example.gymcrm;

import java.sql.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.gymcrm.model.Trainee;
import com.example.gymcrm.model.Trainer;
import com.example.gymcrm.model.TrainingType;
import com.example.gymcrm.model.User;
import com.example.gymcrm.services.implementations.TraineeServiceImpl;
import com.example.gymcrm.services.implementations.TrainerServiceImpl;
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
			TraineeServiceImpl traineeServiceImpl,
			TrainerServiceImpl trainerServiceImpl) {
		return args -> {

			trainerServiceImpl.deleteAll();
			traineeServiceImpl.deleteAll();
			userServiceImpl.deleteAll();
			trainingTypeServiceImpl.deleteAll();

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

			Trainee trainee1 = new Trainee();
			trainee1.setDateOfBith(new Date(0));
			trainee1.setAddress("Santo Tomás");
			trainee1.setUser(user1);

			Trainer trainer1 = new Trainer();
			trainer1.setTrainingType(trainingType1);
			trainer1.setUser(user2);

			traineeServiceImpl.createTrainee(trainee1);
			trainerServiceImpl.createTrainer(trainer1);

			trainer1.getTrainees().add(trainee1);
			trainee1.getTrainers().add(trainer1);

			trainerServiceImpl.updateTrainer(trainer1);
			traineeServiceImpl.updateTrainee(trainee1);
		};
	}
}
