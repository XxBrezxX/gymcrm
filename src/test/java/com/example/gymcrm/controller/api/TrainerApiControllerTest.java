package com.example.gymcrm.controller.api;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.util.*;

import com.example.gymcrm.controllers.TrainerController.TrainerApiController;
import com.example.gymcrm.model.Trainer;
import com.example.gymcrm.model.TrainingType;
import com.example.gymcrm.model.User;
import com.example.gymcrm.model.requests.RegisterTrainerDto;
import com.example.gymcrm.model.requests.UpdateTrainerRequest;
import com.example.gymcrm.services.implementations.models.TrainerServiceImpl;
import com.example.gymcrm.services.implementations.models.UserServiceImpl;
import com.example.gymcrm.services.pureServices.TrainingTypeService;
import com.example.gymcrm.services.web.JwtTokenProvider;

@WebMvcTest(controllers = TrainerApiController.class)
public class TrainerApiControllerTest {

    @MockBean
    private JwtTokenProvider jwtTokenProvider;

    @MockBean
    private TrainerServiceImpl trainerServiceImpl;

    @MockBean
    private UserServiceImpl userServiceImpl;

    @MockBean
    private TrainingTypeService trainingTypeService;

    @Autowired
    private TrainerApiController trainerApiController;

    @Test
    public void registerUser_test() {
        RegisterTrainerDto info = new RegisterTrainerDto();
        info.setFirstName("test");
        info.setLastName("user");
        info.setTrainingType(1L);

        User user = new User();
        user.setFirstName(info.getFirstName());
        user.setLastName(info.getLastName());
        user.setIsActive(true);
        user.setUsername("username");
        user.setPassword(null);

        Map<String, String> response = new HashMap<>();
        response.put("username", user.getUsername());
        response.put("password", user.getPassword());

        TrainingType trainingType = new TrainingType();
        trainingType.setId(1L);
        trainingType.setTrainingTypeName("training");

        Trainer trainer = new Trainer();
        trainer.setUser(user);
        trainer.setTrainingType(trainingType);

        when(userServiceImpl.createUser(any())).thenReturn(user);
        when(trainingTypeService.getTrainingTypeById(1L)).thenReturn(trainingType);
        when(trainerServiceImpl.createTrainer(trainer)).thenReturn(trainer);

        ResponseEntity<Map<String, String>> answer = trainerApiController.registerUser(info);

        assertEquals(ResponseEntity.ok(response), answer);
    }

    @Test
    public void getTrainer_test() {
        Trainer trainer = new Trainer();
        when(trainerServiceImpl.getTrainerByUsername("trainer")).thenReturn(trainer);

        ResponseEntity<String> answer = trainerApiController.getTrainer("trainer");

        assertEquals(ResponseEntity.ok(trainer.toString()), answer);
    }

    @Test
    public void updateTrainee_test() {
        User user = new User();
        Trainer trainer = new Trainer();
        trainer.setUser(user);

        TrainingType trainingType = new TrainingType();
        Trainer updated = new Trainer();
        updated.setTrainingType(trainingType);

        when(trainerServiceImpl.getTrainerByUsername("trainer")).thenReturn(trainer);
        when(trainingTypeService.getTrainingTypeById(1L)).thenReturn(trainingType);
        when(trainerServiceImpl.updateTrainer(any())).thenReturn(updated);

        UpdateTrainerRequest updateTrainerRequest = new UpdateTrainerRequest();
        updateTrainerRequest.setFirstName("first");
        updateTrainerRequest.setLastName("last");
        updateTrainerRequest.setUsername("trainer");
        updateTrainerRequest.setIsActive(true);
        updateTrainerRequest.setSpecialization(1L);

        ResponseEntity<String> answer = trainerApiController.updateTrainee(updateTrainerRequest);

        assertEquals(ResponseEntity.ok(updated.toString()), answer);
    }

}
