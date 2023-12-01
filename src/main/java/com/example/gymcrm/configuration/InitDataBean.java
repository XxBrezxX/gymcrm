package com.example.gymcrm.configuration;

import java.sql.Date;
import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.gymcrm.model.Trainee;
import com.example.gymcrm.model.Trainer;
import com.example.gymcrm.model.Training;
import com.example.gymcrm.model.TrainingType;
import com.example.gymcrm.model.User;
import com.example.gymcrm.services.implementations.models.TraineeServiceImpl;
import com.example.gymcrm.services.implementations.models.TrainerServiceImpl;
import com.example.gymcrm.services.implementations.models.TrainingServiceImpl;
import com.example.gymcrm.services.implementations.models.TrainingTypeServiceImpl;
import com.example.gymcrm.services.implementations.models.UserServiceImpl;

@Configuration
public class InitDataBean {
    @Bean
    public CommandLineRunner initData(
            UserServiceImpl userServiceImpl,
            TrainingTypeServiceImpl trainingTypeServiceImpl,
            TraineeServiceImpl traineeServiceImpl,
            TrainerServiceImpl trainerServiceImpl,
            TrainingServiceImpl trainingServiceImpl) {
        return args -> {

            trainerServiceImpl.deleteAllTraineeTrainerRelations();
            trainingServiceImpl.deleteAll();
            traineeServiceImpl.deleteAll();
            trainerServiceImpl.deleteAll();
            userServiceImpl.deleteAll();
            trainingTypeServiceImpl.deleteAll();

            TrainingType trainingType1 = new TrainingType(null, "Intense");
            TrainingType trainingType2 = new TrainingType(null, "Easy");
            TrainingType trainingType3 = new TrainingType(null, "Medium");

            trainingTypeServiceImpl.createTrainingType(trainingType1);
            trainingTypeServiceImpl.createTrainingType(trainingType2);
            trainingTypeServiceImpl.createTrainingType(trainingType3);

            User user1 = new User(null, "Yessica", "Apolinar", null, null, true);
            Trainee trainee1 = new Trainee();
            trainee1.setDateOfBith(new Date(0));
            trainee1.setAddress("Santo Tomás");
            traineeServiceImpl.createTrainee(trainee1, user1);

            User user2 = new User(null, "Bryan", "Hernandez", null, null, true);
            Trainer trainer1 = new Trainer();
            trainer1.setTrainingType(trainingType1);
            trainerServiceImpl.createTrainer(trainer1, user2);

            // DELETE THIS
            userServiceImpl.updatePassword("123", user2);

            trainer1.getTrainees().add(trainee1);
            trainee1.getTrainers().add(trainer1);

            trainerServiceImpl.updateTrainer(trainer1);
            traineeServiceImpl.updateTrainee(trainee1);

            Training t1 = new Training();
            t1.setTrainee(trainee1);
            t1.setTrainer(trainer1);
            t1.setTrainingName("Bajar de Peso");
            t1.setTrainingType(trainingType1);
            t1.setTrainingDate(LocalDate.parse("2023-01-01"));
            t1.setDuration(20);

            trainingServiceImpl.createTraining(t1);
        };
    }
}
