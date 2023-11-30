package com.example.gymcrm.models.TraineeWorkload;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.gymcrm.model.TrainerWorkload;

@DataJpaTest
class TrainerWorkloadTest {
    @Test
    void testNoArgsConstructor() {
        TrainerWorkload tWorkloadRequest = new TrainerWorkload();
        tWorkloadRequest.setActive(true);
        tWorkloadRequest.setTrainerFirstName("name");
        tWorkloadRequest.setTrainerLastName("last");
        tWorkloadRequest.setTrainerUsername("user");
        tWorkloadRequest.setTrainingDate(null);
        tWorkloadRequest.setTrainingDuration(1);

        assertEquals("user", tWorkloadRequest.getTrainerUsername());
        assertEquals("name", tWorkloadRequest.getTrainerFirstName());
        assertEquals("last", tWorkloadRequest.getTrainerLastName());
        assertTrue(tWorkloadRequest.isActive());
        assertNull(tWorkloadRequest.getTrainingDate());
        assertSame(1, tWorkloadRequest.getTrainingDuration());
    }

    @Test
    void testAllArgsConstructor() {
        TrainerWorkload tWorkloadRequest = new TrainerWorkload(
                "user",
                "name",
                "last",
                true,
                null,
                1);

        assertEquals("user", tWorkloadRequest.getTrainerUsername());
        assertEquals("name", tWorkloadRequest.getTrainerFirstName());
        assertEquals("last", tWorkloadRequest.getTrainerLastName());
        assertTrue(tWorkloadRequest.isActive());
        assertNull(tWorkloadRequest.getTrainingDate());
        assertSame(1, tWorkloadRequest.getTrainingDuration());
    }
}
