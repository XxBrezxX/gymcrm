package com.example.gymcrm.models.TraineeTest;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.gymcrm.model.Trainee;
import com.example.gymcrm.model.Trainer;

@SpringBootTest
class TraineeTest {
    @Test
    void testAllArgsConstructor() {
        Trainee trainee = new Trainee(1L, Date.valueOf(LocalDate.of(2000, 1, 1)), "123 Main St.", null, null);

        assertEquals(1L, trainee.getId());
        assertEquals(Date.valueOf(LocalDate.of(2000, 1, 1)), trainee.getDateOfBith());
        assertEquals("123 Main St.", trainee.getAddress());
        assertNull(trainee.getUser());
        assertNull(trainee.getTrainers());
    }

    @Test
    void testSetTrainers() {
        Trainee trainee = new Trainee();
        trainee.setId(1L);

        Set<Trainer> trainers = new HashSet<>();
        trainee.setTrainers(trainers);

        assertSame(trainers, trainee.getTrainers());
        assertEquals(1L, trainee.getId());
    }

}
