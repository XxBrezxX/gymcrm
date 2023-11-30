package com.example.gymcrm.models.TrainerMonthlySummary;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.gymcrm.model.TrainerMonthlySummary;

@DataJpaTest
class TrainerMonthlySummaryTest {
    @Test
    void testAllArgsConstructor() {
        TrainerMonthlySummary trainerMonthlySummary = new TrainerMonthlySummary(
                "Test1",
                "TestName",
                "TestLast",
                "Ok",
                null);

        assertEquals("Test1", trainerMonthlySummary.getTrainerUsername());
        assertEquals("TestName", trainerMonthlySummary.getTrainerFirstName());
        assertEquals("TestLast", trainerMonthlySummary.getTrainerLastName());
        assertEquals("Ok", trainerMonthlySummary.getTrainerStatus());
        assertNull(trainerMonthlySummary.getYearlyTrainingSummary());
    }

    @Test
    void testNoArgsContructor() {
        TrainerMonthlySummary summary = new TrainerMonthlySummary();
        summary.setTrainerUsername("user");
        summary.setTrainerFirstName("name");
        summary.setTrainerLastName("last");
        summary.setTrainerStatus("ok");
        summary.setYearlyTrainingSummary(null);

        assertEquals("user", summary.getTrainerUsername());
        assertEquals("name", summary.getTrainerFirstName());
        assertEquals("last", summary.getTrainerLastName());
        assertEquals("ok", summary.getTrainerStatus());
        assertNull(summary.getYearlyTrainingSummary());
    }
}
