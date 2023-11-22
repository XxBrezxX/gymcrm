package com.example.gymcrm.TraineeTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import com.example.gymcrm.model.User;

public class TraineeTest {

    @Test
    public void testEqual() {
        User user1 = new User(1L, "Yessica", "Apolinar", "laApol", "123", true);
        User user2 = new User(1L, "Yessica", "Apolinar", "laApol", "123", true);

        assertEquals(user1, user2);
    }

    @Test
    public void testDifferent() {
        User user1 = new User(1L, "Yessica", "Apolinar", "laApol", "123", true);
        User user3 = new User(2L, "Bryan", "Hernández", "itsBrez", "123", true);

        assertNotEquals(user1, user3);
    }
}
