package com.example.gymcrm.models.UserTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.gymcrm.model.User;

@DataJpaTest
class UserTest {

    @Test
    void testGettersAndSetters() {
        User user = new User(1L, "John", "Doe", "johndoe", "password", true);
        assertSame(1L, user.getId());
        assertEquals("John", user.getFirstName());
        assertEquals("Doe", user.getLastName());
        assertEquals("johndoe", user.getUsername());
        assertEquals("password", user.getPassword());
        assertTrue(user.getIsActive());
        user.setId(2L);
        assertSame(2L, user.getId());
        user.setFirstName("Jane");
        assertEquals("Jane", user.getFirstName());
        user.setLastName("Doe");
        assertEquals("Doe", user.getLastName());
        user.setUsername("janedoe");
        assertEquals("janedoe", user.getUsername());
        user.setPassword("new_password");
        assertEquals("new_password", user.getPassword());
        user.setIsActive(false);
        assertFalse(user.getIsActive());
    }

}