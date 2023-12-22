package com.example.gymcrm.controller.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;

import com.example.gymcrm.controllers.TraineeController.TraineeApiController;
import com.example.gymcrm.model.Trainee;
import com.example.gymcrm.model.Trainer;
import com.example.gymcrm.model.User;
import com.example.gymcrm.model.requests.DeleteRequest;
import com.example.gymcrm.model.requests.RegisterTraineeDto;
import com.example.gymcrm.model.requests.UpdateTraineesTrainerListRequest;
import com.example.gymcrm.services.implementations.models.TraineeServiceImpl;
import com.example.gymcrm.services.implementations.models.TrainerServiceImpl;
import com.example.gymcrm.services.implementations.models.UserServiceImpl;
import com.example.gymcrm.services.web.JwtTokenProvider;

@WebMvcTest(controllers = TraineeApiController.class)
public class TraineeApiControllerTest {

    @Mock
    private Model model;

    @MockBean
    private JwtTokenProvider provider;

    @MockBean
    private TraineeServiceImpl traineeServiceImpl;

    @MockBean
    private UserServiceImpl userServiceImpl;

    @MockBean
    private TrainerServiceImpl trainerServiceImpl;

    @Autowired
    private TraineeApiController traineeApiController;

    @Test
    public void testRegisterUser() {
        User user = new User();
        user.setUsername("username");
        user.setPassword("password");
        Trainee trainee = new Trainee();

        when(userServiceImpl.createUser(any())).thenReturn(user);
        when(traineeServiceImpl.createTrainee(trainee)).thenReturn(trainee);

        Map<String, String> data = new HashMap<>();
        data.put("username", "username");
        data.put("password", "password");

        ResponseEntity<Map<String, String>> expected = ResponseEntity.ok(data);

        RegisterTraineeDto info = new RegisterTraineeDto();
        info.setFirstName("user");
        info.setLastName("name");

        ResponseEntity<Map<String, String>> answer = traineeApiController.registerUser(info);

        assertEquals(expected, answer);
    }

    @Test
    public void testGetTrainer() {
        Trainee trainee = new Trainee();
        User user = new User();
        trainee.setUser(user);
        String username = "user";

        when(traineeServiceImpl.getTraineeByUsername("user")).thenReturn(trainee);

        ResponseEntity<String> answer = traineeApiController.getTrainer(username);

        assertEquals(ResponseEntity.ok(trainee.toString()), answer);
    }

    @Test
    public void testUpdateTrainee() {
        Trainee trainee = new Trainee();
        User user = new User();
        user.setUsername("user");
        trainee.setUser(user);

        when(traineeServiceImpl.getTraineeByUsername("user")).thenReturn(trainee);
        when(userServiceImpl.updateUser(user)).thenReturn(user);
        when(traineeServiceImpl.updateTrainee(trainee)).thenReturn(trainee);

        ResponseEntity<String> answer = traineeApiController.updateTrainee(trainee);

        assertEquals(ResponseEntity.ok(trainee.toString()), answer);
    }

    @Test
    public void testDelete() {
        DeleteRequest deleteRequest = new DeleteRequest();
        deleteRequest.setUsername("user");

        traineeApiController.deleteTrainee(deleteRequest);

        verify(traineeServiceImpl, atLeastOnce()).deleteTraineeByUsername(deleteRequest.getUsername());
    }

    @Test
    public void testUpdateTrainerList() {
        UpdateTraineesTrainerListRequest data = new UpdateTraineesTrainerListRequest();
        data.setUsername("userTrainee");

        List<String> trainers = new ArrayList<>();
        trainers.add("user");
        data.setTrainers(trainers);

        Trainee trainee = new Trainee();
        User user = new User();
        trainee.setUser(user);

        when(traineeServiceImpl.getTraineeByUsername("userTrainee")).thenReturn(trainee);
        when(trainerServiceImpl.getTrainerByUsername("user")).thenReturn(new Trainer());
        when(traineeServiceImpl.updateTrainee(trainee)).thenReturn(trainee);

        ResponseEntity<String> answer = traineeApiController.updateTrainersList(data);

        assertEquals(ResponseEntity.ok(trainee.toString()), answer);
    }
}
