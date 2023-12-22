package com.example.gymcrm.services.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class JwtTokenProviderTest {

    @InjectMocks
    private JwtTokenProvider jwtTokenProvider;

    private final String username = "john";

    // Tu test existente para generar tokens.
    @Test
    public void testGenerateToken() {
        String token = jwtTokenProvider.generateToken(username);
        assertNotNull(token);
        assertFalse(token.isEmpty());
    }

    // Tu test existente para obtener nombres de usuario.
    @Test
    public void testGetUsernameFromJwt() {
        String token = jwtTokenProvider.generateToken(username);
        String extractedUserName = jwtTokenProvider.getUsernameFromJwt(token);
        assertEquals(username, extractedUserName);
    }

    // Tu test existente para validar tokens.
    @Test
    public void testValidateToken() {
        String token = jwtTokenProvider.generateToken(username);
        boolean isValid = jwtTokenProvider.validateToken(token);
        assertTrue(isValid);

        assertFalse(jwtTokenProvider.validateToken(token + "invalid"));
    }

    // Nuevo test: ¿Cómo se comportaría validateToken si el token es null?
    @Test
    public void testValidateNullToken() {
        assertFalse(jwtTokenProvider.validateToken(null));
    }
}
