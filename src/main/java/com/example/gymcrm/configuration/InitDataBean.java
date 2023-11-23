package com.example.gymcrm.configuration;

import java.sql.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.example.gymcrm.model.Trainee;
import com.example.gymcrm.model.Trainer;
import com.example.gymcrm.model.TrainingType;
import com.example.gymcrm.model.User;
import com.example.gymcrm.services.implementations.TraineeServiceImpl;
import com.example.gymcrm.services.implementations.TrainerServiceImpl;
import com.example.gymcrm.services.implementations.TrainingTypeServiceImpl;
import com.example.gymcrm.services.implementations.UserServiceImpl;

@Configuration
public class InitDataBean {
    @Bean
    public CommandLineRunner initData(
            UserServiceImpl userServiceImpl,
            TrainingTypeServiceImpl trainingTypeServiceImpl,
            TraineeServiceImpl traineeServiceImpl,
            TrainerServiceImpl trainerServiceImpl) {
        return args -> {

            trainerServiceImpl.deleteAllTraineeTrainerRelations();
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

            User user1 = new User(null, "Bryan", "Hernandez", null, null, true);
            Trainee trainee1 = new Trainee();
            trainee1.setDateOfBith(new Date(0));
            trainee1.setAddress("Santo Tomás");
            traineeServiceImpl.createTrainee(trainee1, user1);

            User user2 = new User(null, "Bryan", "Hernandez", null, null, true);
            Trainer trainer1 = new Trainer();
            trainer1.setTrainingType(trainingType1);
            trainerServiceImpl.createTrainer(trainer1, user2);

            
            trainer1.getTrainees().add(trainee1);
            trainee1.getTrainers().add(trainer1);

            trainerServiceImpl.updateTrainer(trainer1);
            traineeServiceImpl.updateTrainee(trainee1);
        };
    }
}
