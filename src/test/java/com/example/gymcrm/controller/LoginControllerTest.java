package com.example.gymcrm.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.gymcrm.controllers.LoginController;

@DataJpaTest
class LoginControllerTest {

    @Test
    public void testLogin() {
        LoginController loginController = new LoginController();
        String response = loginController.login();

        assertEquals("login", response);
    }
}