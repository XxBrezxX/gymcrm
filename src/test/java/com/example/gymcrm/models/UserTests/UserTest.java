package com.example.gymcrm.models.UserTests;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.gymcrm.model.User;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserTest {

    @Test
    void testGettersAndSetters() {
        User user = new User(1L, "John", "Doe", "johndoe", "password", true);
        assertSame(1L, user.getId());
        assertSame("John", user.getFirstName());
        assertSame("Doe", user.getLastName());
        assertSame("johndoe", user.getUsername());
        assertSame("password", user.getPassword());
        assertTrue(user.getIsActive());

        user.setId(2L);
        assertSame(2L, user.getId());
        user.setFirstName("Jane");
        assertSame("Jane", user.getFirstName());
        user.setLastName("Doe");
        assertSame("Doe", user.getLastName());
        user.setUsername("janedoe");
        assertSame("janedoe", user.getUsername());
        user.setPassword("new_password");
        assertSame("new_password", user.getPassword());
        user.setIsActive(false);
        assertFalse(user.getIsActive());
    }

}