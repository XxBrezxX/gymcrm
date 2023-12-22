package com.example.gymcrm.controller.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.gymcrm.controllers.UserController.UserApiController;
import com.example.gymcrm.model.User;
import com.example.gymcrm.model.requests.UpdatePaswordRequest;
import com.example.gymcrm.model.requests.UpdateStatusRequest;
import com.example.gymcrm.services.implementations.models.UserServiceImpl;
import com.example.gymcrm.services.web.JwtTokenProvider;

@WebMvcTest(controllers = UserApiController.class)
public class UserApiControllerTest {

    @MockBean
    private JwtTokenProvider jTokenProvider;

    @MockBean
    private UserServiceImpl userServiceImpl;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserApiController userApiController;

    @Test
    public void testUpdateActive() {
        UpdateStatusRequest data = new UpdateStatusRequest();
        data.setIsActive(true);
        data.setUsername("user");

        User user = new User();

        when(userServiceImpl.findByUsername("user")).thenReturn(user);
        when(userServiceImpl.updateUser(user)).thenReturn(user);

        ResponseEntity<String> expected = ResponseEntity.ok("OK");

        ResponseEntity<String> answer = userApiController.updateActive(data);

        assertEquals(expected, answer);
    }

    @Test
    public void testUpdatePassword_NotMatching(){
        UpdatePaswordRequest data = new UpdatePaswordRequest();
        data.setUsername("user");
        data.setNewPassword("new");
        data.setOldPassword("old");

        User user = new User();
        user.setPassword("niu");

        when(userServiceImpl.findByUsername("user")).thenReturn(user);

        assertThrows(BadCredentialsException.class, ()->{
            userApiController.updatePassword(data);
        });
    }

    @Test
    public void testUpdatePassword_Matches(){
        UpdatePaswordRequest data = new UpdatePaswordRequest();
        data.setUsername("user");
        data.setNewPassword("new");
        data.setOldPassword("old");

        User user = new User();
        user.setPassword("old");

        when(userServiceImpl.findByUsername("user")).thenReturn(user);
        when(passwordEncoder.matches("old", "old")).thenReturn(true);

        userApiController.updatePassword(data);

        verify(userServiceImpl, atLeastOnce()).updatePassword("new", user);
    }

}
