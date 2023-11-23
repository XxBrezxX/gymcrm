package com.example.gymcrm.UserTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import com.example.gymcrm.model.User;

public class UserTest {

    @Test
    public void testEqual() {
        User user1 = new User(null, "Yessica", "Apolinar", "laApol", "123", true);
        User user2 = new User(null, "Yessica", "Apolinar", "laApol", "123", true);

        assertEquals(user1, user2);
    }

    @Test
    public void testDifferent() {
        User user1 = new User(null, "Yessica", "Apolinar", "laApol", "123", true);
        User user3 = new User(null, "Bryan", "Hernández", "itsBrez", "123", true);

        assertNotEquals(user1, user3);
    }
}
