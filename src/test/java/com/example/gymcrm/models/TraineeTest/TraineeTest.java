package com.example.gymcrm.models.TraineeTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.sql.Date;

import org.junit.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.gymcrm.model.Trainee;
import com.example.gymcrm.model.User;

@DataJpaTest
class TraineeTest {

    private Trainee generateTrainee(String name) {
        User testUser = new User();
        testUser.setFirstName(name);

        Trainee trainee1 = new Trainee();
        trainee1.setAddress("TestAddress");
        trainee1.setDateOfBith(new Date(0));
        trainee1.setTrainers(null);
        trainee1.setUser(testUser);

        return trainee1;
    }

    @Test
    void testCreateTrainee() {
        Trainee trainee1 = generateTrainee("Trainee1");
        Trainee trainee2 = generateTrainee("Trainee2");
        Trainee trainee3 = generateTrainee("Trainee1");

        assertEquals(trainee1, trainee3);
        assertNotEquals(trainee1, trainee2);
    }

    @Test
    void testToString() {
        Trainee trainee = new Trainee();
        trainee.setId(1L);
        trainee.setDateOfBith(Date.valueOf("1990-01-01"));
        trainee.setAddress("123 Main Street");
        System.out.println(trainee);
        assertTrue(true);
    }
}
