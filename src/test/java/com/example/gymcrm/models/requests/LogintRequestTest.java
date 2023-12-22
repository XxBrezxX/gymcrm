package com.example.gymcrm.models.requests;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.gymcrm.model.requests.LoginRequest;

@DataJpaTest
public class LogintRequestTest {

    @Test
    public void createRequest() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("user");
        loginRequest.setPassword("pass");

        LoginRequest loginRequest2 = new LoginRequest("user2", "pass2");

        assertNotNull(loginRequest);
        assertNotNull(loginRequest2);
        assertSame("user", loginRequest.getUsername());
        assertSame("pass", loginRequest.getPassword());
    }

}
