package com.example.gymcrm.models.Training;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.gymcrm.model.Training;

@DataJpaTest
class TrainingTest {
    @Test
    void testNoArgsConstructor() {
        Training training = new Training();
        training.setDuration(1);
        training.setId(1L);
        training.setTrainee(null);
        training.setTrainer(null);
        training.setTrainingDate(null);
        training.setTrainingName("name");
        training.setTrainingType(null);

        assertSame(1, training.getDuration());
        assertSame(1L, training.getId());
        assertNull(training.getTrainee());
        assertNull(training.getTrainer());
        assertNull(training.getTrainingDate());
        assertNull(training.getTrainingType());
        assertEquals("name", training.getTrainingName());
    }

    @Test
    void testAllArgsConstructor() {
        Training training = new Training(1L, null, null, "name", null, null, 1);

        assertSame(1, training.getDuration());
        assertSame(1L, training.getId());
        assertNull(training.getTrainee());
        assertNull(training.getTrainer());
        assertNull(training.getTrainingDate());
        assertNull(training.getTrainingType());
        assertEquals("name", training.getTrainingName());
    }
}
