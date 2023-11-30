package com.example.gymcrm.models.TraineeWorkload;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.gymcrm.model.TrainerWorkloadRequest;
import com.example.gymcrm.model.TrainerWorkloadRequest.ActionType;

@DataJpaTest
public class TraineeWorkloadRequestTest {
    @Test
    void testAllArgsConstructor() {
        TrainerWorkloadRequest tWorkloadRequest = new TrainerWorkloadRequest(
                "user",
                "name",
                "last",
                true,
                null,
                1,
                ActionType.ADD);

        assertEquals("user", tWorkloadRequest.getTrainerUsername());
        assertEquals("name", tWorkloadRequest.getTrainerFirstName());
        assertEquals("last", tWorkloadRequest.getTrainerLastName());
        assertTrue(tWorkloadRequest.isActive());
        assertNull(tWorkloadRequest.getTrainingDate());
        assertSame(1, tWorkloadRequest.getTrainingDuration());
        assertEquals(ActionType.ADD, tWorkloadRequest.getActionType());
    }

    @Test
    void testNoArgsConstructor() {
        TrainerWorkloadRequest tWorkloadRequest = new TrainerWorkloadRequest();
        tWorkloadRequest.setActionType(ActionType.ADD);
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
        assertEquals(ActionType.ADD, tWorkloadRequest.getActionType());
    }
}
