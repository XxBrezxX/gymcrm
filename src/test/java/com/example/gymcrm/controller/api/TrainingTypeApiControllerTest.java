package com.example.gymcrm.controller.api;

import com.example.gymcrm.controllers.TrainingTypeController.TrainingTypeApiController;
import com.example.gymcrm.model.TrainingType;
import com.example.gymcrm.services.implementations.models.TrainingTypeServiceImpl;
import com.example.gymcrm.services.web.JwtTokenProvider;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@WebMvcTest(controllers = TrainingTypeApiController.class)
public class TrainingTypeApiControllerTest {

    @MockBean
    private JwtTokenProvider provider;

    @MockBean
    private TrainingTypeServiceImpl trainingTypeServiceImpl;

    @Autowired
    private TrainingTypeApiController trainingTypeApiController;

    @Test
    public void getTest() {
        List<TrainingType> list = new ArrayList<>();
        ResponseEntity<List<TrainingType>> responseEntity = ResponseEntity.ok(list);

        when(trainingTypeServiceImpl.getAllTrainingTypes()).thenReturn(list);

        ResponseEntity<List<TrainingType>> answer = trainingTypeApiController.getTrainingTypes();

        assertEquals(responseEntity, answer);
        assertEquals(HttpStatus.OK, answer.getStatusCode());
    }
}