package com.example.gymcrm.controller.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;

import com.example.gymcrm.controllers.TraineeController.TraineeController;
import com.example.gymcrm.model.Trainee;
import com.example.gymcrm.model.User;
import com.example.gymcrm.services.implementations.models.TraineeServiceImpl;
import com.example.gymcrm.services.implementations.models.TrainingServiceImpl;
import com.example.gymcrm.services.implementations.models.UserServiceImpl;
import com.example.gymcrm.services.web.JwtTokenProvider;

@WebMvcTest(controllers = TraineeController.class)
public class TraineeControllerTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private Model model;

    @MockBean
    private JwtTokenProvider provider;

    @MockBean
    private TraineeServiceImpl traineeServiceImpl;

    @MockBean
    private UserServiceImpl userServiceImpl;

    @MockBean
    private TrainingServiceImpl trainingServiceImpl;

    @Autowired
    private TraineeController traineeController;

    @Test
    public void testRegisterUser() {
        Map<String, String> expected = new HashMap<>();
        expected.put("username", null);
        expected.put("password", null);
        ResponseEntity<Map<String, String>> expectedEntity = ResponseEntity.ok(expected);

        User user = new User();
        Trainee trainee = new Trainee();
        user.setFirstName("testUser");

        when(userServiceImpl.createUser(user)).thenReturn(user);
        when(traineeServiceImpl.createTrainee(trainee)).thenReturn(trainee);

        ResponseEntity<Map<String, String>> resposeEntity = traineeController.registerUser(user, trainee);

        assertEquals(expectedEntity, resposeEntity);
    }

    @Test
    public void testShowRegistrationForm() {
        String response = traineeController.showRegistrationForm(model);
        assertSame("controllers/trainee/registerTrainee", response);
    }

    @Test
    public void testNewPassword_SuccessAuth() {
        String expected = "redirect:/login";
        User user = new User();
        user.setUsername("testUser");

        when(userServiceImpl.findByUsername("testUser")).thenReturn(user);

        String actual = traineeController.newPassword(user.getUsername(), "testPassword", request, response);

        verify(userServiceImpl, atLeastOnce()).updatePassword("testPassword", user);
        assertSame(expected, actual);
    }

    @Test
    public void testNewPassword_FailedAuth() {
        Authentication authentication = mock(Authentication.class);
        SecurityContext securityContext = mock(SecurityContext.class);
        securityContext.setAuthentication(authentication);
        SecurityContextHolder.setContext(securityContext);

        String expected = "redirect:/login";
        User user = new User();
        user.setUsername("testUser");

        when(userServiceImpl.findByUsername("testUser")).thenReturn(user);
        when(securityContext.getAuthentication()).thenReturn(authentication);

        String actual = traineeController.newPassword(user.getUsername(), "testPassword", request, response);

        verify(userServiceImpl, atLeastOnce()).updatePassword("testPassword", user);
        assertSame(expected, actual);
    }

    @Test
    public void testGetTrainees() {
        when(traineeServiceImpl.getAllTrainees()).thenReturn(new ArrayList<Trainee>());

        String actual = traineeController.getTrainees(model);

        String expected = "controllers/trainee/listTrainee";
        assertSame(expected, actual);
    }

    @Test
    public void testGetTrainer() {
        String answer = traineeController.getTrainer("test", model);
        String expected = "controllers/trainee/modifyTrainee";

        assertSame(expected, answer);
    }

    @Test
    public void testUpdateStatus() {
        User user = new User();
        user.setIsActive(false);
        User user2 = new User();
        user2.setIsActive(true);

        when(userServiceImpl.findByUsername("test")).thenReturn(user);
        when(userServiceImpl.findByUsername("test2")).thenReturn(user2);

        String ans = traineeController.updateStatus("test");
        String ans2 = traineeController.updateStatus("test2");
        String exp = "redirect:/trainees/list";

        assertSame(exp, ans);
        assertSame(exp, ans2);
    }

    @Test
    public void testDeleteTrainee() {
        String ans = traineeController.deleteTrainee("test");
        String exp = "redirect:/trainees/list";

        assertSame(exp, ans);
    }
}
