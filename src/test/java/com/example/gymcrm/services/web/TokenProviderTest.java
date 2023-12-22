package com.example.gymcrm.services.web;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TokenProviderTest {

    @InjectMocks
    private TokenProvider tokenProvider;

    @Test
    public void createToken_test() {
        String token = tokenProvider.createToken("token");
        assertNotNull(token);
        assertFalse(token.isEmpty());
    }

}
