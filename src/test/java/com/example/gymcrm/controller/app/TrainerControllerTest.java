package com.example.gymcrm.controller.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.gymcrm.controllers.TrainerController.TrainerController;
import com.example.gymcrm.model.Trainer;
import com.example.gymcrm.model.User;
import com.example.gymcrm.services.implementations.models.TrainerServiceImpl;
import com.example.gymcrm.services.implementations.models.UserServiceImpl;
import com.example.gymcrm.services.web.JwtTokenProvider;

@WebMvcTest(controllers = TrainerController.class)
public class TrainerControllerTest {

    @MockBean
    private WebClient webClient;

    @MockBean
    private HttpServletRequest httpServletRequest;

    @MockBean
    private HttpServletResponse httpServletResponse;

    @MockBean
    private JwtTokenProvider jwtTokenProvider;

    @MockBean
    private Model model;

    @MockBean
    private TrainerServiceImpl trainerServiceImpl;

    @MockBean
    private UserServiceImpl userServiceImpl;

    @Autowired
    private TrainerController trainerController;

    @Test
    public void testShowRegistrationForm() {
        String expected = "controllers/trainer/registerTrainer";
        String answer = trainerController.showRegistrationForm(model);

        assertSame(expected, answer);
    }

    @Test
    public void testRegisterTrainer() {
        Map<String, String> data = new HashMap<>();
        data.put("username", "test");
        data.put("password", "pass");

        User user = new User();
        user.setUsername("test");
        user.setPassword("pass");
        Trainer trainer = new Trainer();

        when((userServiceImpl.createUser(user))).thenReturn(user);
        when(trainerServiceImpl.createTrainer(trainer)).thenReturn(trainer);

        ResponseEntity<Map<String, String>> answer = trainerController.registerTrainer(user, trainer);

        assertEquals(ResponseEntity.ok(data), answer);
    }

    @Test
    public void testGetTrainers() {
        List<Trainer> trainers = new ArrayList<>();

        when(trainerServiceImpl.getAllTrainers()).thenReturn(trainers);

        String expected = "controllers/trainer/listTrainer";
        String answer = trainerController.getTrainers(model);

        assertSame(expected, answer);
    }

    @Test
    public void testGetTrainer() {
        Trainer trainer = new Trainer();
        User user = new User();
        user.setUsername("user");

        when(trainerServiceImpl.getTrainerByUsername("user")).thenReturn(trainer);

        String answer = trainerController.getTrainer("user", model);
        String expected = "controllers/trainer/modifyTrainer";

        assertSame(expected, answer);
    }

    @Test
    public void testNewPassword() {
        Authentication authentication = mock(Authentication.class);
        SecurityContext securityContext = mock(SecurityContext.class);
        securityContext.setAuthentication(authentication);
        SecurityContextHolder.setContext(securityContext);

        String expected = "redirect:/login";
        User user = new User();
        user.setUsername("user");

        when(userServiceImpl.findByUsername("user")).thenReturn(user);
        when(securityContext.getAuthentication()).thenReturn(authentication);

        String answer = trainerController.newPassword(user.getUsername(), "pass", httpServletRequest,
                httpServletResponse);

        verify(userServiceImpl, atLeastOnce()).updatePassword("pass", user);
        assertSame(expected, answer);
    }

    @Test
    public void testNewPassword_SuccessAuth() {
        String expected = "redirect:/login";
        User user = new User();
        user.setUsername("testUser");

        when(userServiceImpl.findByUsername("testUser")).thenReturn(user);

        String actual = trainerController.newPassword(user.getUsername(), "testPassword", httpServletRequest,
                httpServletResponse);

        verify(userServiceImpl, atLeastOnce()).updatePassword("testPassword", user);
        assertSame(expected, actual);
    }

    // @Test
    // public void testGetMethodName() {

    // String answer = trainerController.getMethodName("user", model);
    // String expected = "controllers/trainer/generateReport";

    // assertSame(expected, answer);
    // }

    @Test
    public void testUpdateStatus() {
        User user = new User();
        user.setIsActive(false);
        User user2 = new User();
        user2.setIsActive(true);

        when(userServiceImpl.findByUsername("test")).thenReturn(user);
        when(userServiceImpl.findByUsername("test2")).thenReturn(user2);

        String ans = trainerController.updateStatus("test");
        String ans2 = trainerController.updateStatus("test2");
        String exp = "redirect:/trainers/list";

        assertSame(exp, ans);
        assertSame(exp, ans2);
    }
}
