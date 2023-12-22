package com.example.gymcrm.controller.app;

import static org.junit.Assert.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import com.example.gymcrm.controllers.LoginController;
import com.example.gymcrm.model.requests.LoginRequest;
import com.example.gymcrm.services.web.JwtTokenProvider;

@WebMvcTest(controllers = LoginController.class)
public class LoginControllerTest {

    @MockBean
    private AuthenticationManager authenticationManager;

    @MockBean
    private JwtTokenProvider jwtTokenProvider;

    @MockBean
    private Authentication authentication;

    @MockBean
    private UsernamePasswordAuthenticationToken authenticationToken;

    @Autowired
    private LoginController loginController;

    private static final String TEST_USERNAME = "username";
    private static final String TEST_PASSWORD = "password";
    private static final String TEST_JWT = "testJwt";

    @Test
    public void getHome_test() {
        String expected = "home";
        String answer = loginController.getMethodName();

        assertSame(expected, answer);
    }

    @Test
    public void successfulLogin() {
        LoginRequest request = new LoginRequest(TEST_USERNAME, TEST_PASSWORD);
        Authentication auth = new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(auth);
        when(jwtTokenProvider.generateToken(TEST_USERNAME)).thenReturn(TEST_JWT);

        ResponseEntity<String> result = loginController.login(request);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(TEST_JWT, result.getBody());
    }

    @Test
    public void failedLogin() {
        LoginRequest request = new LoginRequest(TEST_USERNAME, TEST_PASSWORD);

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenThrow(new BadCredentialsException("Invalid credentials"));

        ResponseEntity<String> result = loginController.login(request);

        assertEquals(HttpStatus.UNAUTHORIZED, result.getStatusCode());
        assertEquals("Failed to authenticate user.", result.getBody());
    }
}
