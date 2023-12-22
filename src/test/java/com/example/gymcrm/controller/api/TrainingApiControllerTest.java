package com.example.gymcrm.controller.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.*;

import com.example.gymcrm.controllers.TrainingController.TrainingApiController;
import com.example.gymcrm.model.Trainee;
import com.example.gymcrm.model.Trainer;
import com.example.gymcrm.model.Training;
import com.example.gymcrm.model.TrainingType;
import com.example.gymcrm.model.User;
import com.example.gymcrm.model.requests.AddTrainingRequest;
import com.example.gymcrm.model.requests.GetTrainingsRequest;
import com.example.gymcrm.services.implementations.models.TraineeServiceImpl;
import com.example.gymcrm.services.implementations.models.TrainerServiceImpl;
import com.example.gymcrm.services.implementations.models.TrainingServiceImpl;
import com.example.gymcrm.services.web.JwtTokenProvider;

@WebMvcTest(controllers = TrainingApiController.class)
public class TrainingApiControllerTest {

    @MockBean
    private JwtTokenProvider jwtTokenProvider;

    @MockBean
    private TrainingServiceImpl trainingServiceImpl;

    @MockBean
    private TraineeServiceImpl traineeServiceImpl;

    @MockBean
    private TrainerServiceImpl trainerServiceImpl;

    @Autowired
    private TrainingApiController trainingApiController;

    @Test
    public void getTrainings_filters_test() {
        GetTrainingsRequest data = new GetTrainingsRequest();
        data.setFrom(LocalDate.of(2021, 1, 1));
        data.setTo(LocalDate.of(2023, 1, 1));
        data.setTrainerName("raul");
        data.setTrainingTypeName("training");
        data.setUsername("user");

        List<Training> trainings = new ArrayList<>();
        Trainer raul = new Trainer();
        Trainer pedro = new Trainer();
        Trainer jose = new Trainer();
        TrainingType tt1 = new TrainingType();
        TrainingType tt2 = new TrainingType();
        TrainingType tt3 = new TrainingType();

        tt1.setTrainingTypeName("training");
        tt2.setTrainingTypeName("training2");

        raul.setUser(new User(null, "raul", null, null, null, null));
        pedro.setUser(new User(null, "pedro", null, null, null, null));
        jose.setUser(new User(null, "jose", null, null, null, null));

        trainings.add(new Training(null, null, raul, "training", tt1, LocalDate.of(2019, 1, 1), 1));
        trainings.add(new Training(null, null, pedro, "training1", tt2, LocalDate.of(2020, 1, 1), 1));
        trainings.add(new Training(null, null, jose, "training2", tt3, LocalDate.of(2021, 1, 1), 1));
        trainings.add(new Training(null, null, pedro, "training", tt2, LocalDate.of(2022, 1, 1), 1));
        trainings.add(new Training(null, null, jose, "training1", tt1, LocalDate.of(2023, 1, 1), 1));
        trainings.add(new Training(null, null, raul, "training2", tt2, LocalDate.of(2024, 1, 1), 1));

        when(trainingServiceImpl.getAllTrainingsByUsername("user")).thenReturn(trainings);

        ResponseEntity<String> answer = trainingApiController.getTrainings(data);

        assertEquals(ResponseEntity.ok(new ArrayList<Training>().toString()), answer);
    }

    @Test
    public void getTrainings_test() {
        GetTrainingsRequest data = new GetTrainingsRequest();
        data.setUsername("user");

        List<Training> trainings = new ArrayList<>();

        when(trainingServiceImpl.getAllTrainingsByUsername("user")).thenReturn(trainings);

        ResponseEntity<String> answer = trainingApiController.getTrainings(data);

        assertEquals(ResponseEntity.ok(trainings.toString()), answer);
    }

    @Test
    public void createTraining_test() {
        Trainee trainee = new Trainee();
        Trainer trainer = new Trainer();

        AddTrainingRequest data = new AddTrainingRequest();
        data.setDate(LocalDate.of(2023, 01, 01));
        data.setDuration(1);
        data.setName("name");
        data.setTrainee("trainee");
        data.setTrainer("trainer");

        Training training = new Training();
        training.setTrainee(trainee);
        training.setTrainer(trainer);
        training.setTrainingName(data.getName());
        training.setTrainingDate(data.getDate());
        training.setDuration(data.getDuration());

        when(traineeServiceImpl.getTraineeByUsername("trainee")).thenReturn(trainee);
        when(trainerServiceImpl.getTrainerByUsername("trainer")).thenReturn(trainer);
        when(trainingServiceImpl.createTraining(training)).thenReturn(training);

        ResponseEntity<String> answer = trainingApiController.createTraining(data);

        assertEquals(ResponseEntity.ok("OK"), answer);
    }
}
