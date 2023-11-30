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
        assertEquals(1L, user.getId()); // test getter
        assertEquals("John", user.getFirstName()); // test getter
        assertEquals("Doe", user.getLastName()); // test getter
        assertEquals("johndoe", user.getUsername()); // test getter
        assertEquals("password", user.getPassword()); // test getter
        assertTrue(user.getIsActive()); // test getter
        user.setId(2L); // test setter
        assertEquals(2L, user.getId()); // test getter after setter
        user.setFirstName("Jane"); // test setter
        assertEquals("Jane", user.getFirstName()); // test getter after setter
        user.setLastName("Doe"); // test setter
        assertEquals("Doe", user.getLastName()); // test getter after setter
        user.setUsername("janedoe"); // test setter
        assertEquals("janedoe", user.getUsername()); // test getter after setter
        user.setPassword("new_password"); // test setter
        assertEquals("new_password", user.getPassword()); // test getter after setter
        user.setIsActive(false); // test setter
        assertFalse(user.getIsActive()); // test getter after setter
    }

}