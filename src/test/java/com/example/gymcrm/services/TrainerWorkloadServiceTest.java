package com.example.gymcrm.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.gymcrm.model.TrainerMonthlySummary;
import com.example.gymcrm.services.implementations.web.TrainerWorkloadService;
import com.example.gymcrm.services.web.WorkloadServiceImpl;

import reactor.core.publisher.Mono;

@DataJpaTest
class TrainerWorkloadServiceTest {

    @Mock
    private WorkloadServiceImpl workloadServiceImpl;

    @InjectMocks
    private TrainerWorkloadService trainerWorkloadService;

    @Test
    void testGetSummary() {
        MockitoAnnotations.openMocks(this);

        String trainerUsername = "Bryan.Hernandez";
        TrainerMonthlySummary summary = new TrainerMonthlySummary();

        when(workloadServiceImpl.getSummary(trainerUsername))
                .thenReturn(Mono.just(summary));

        Mono<TrainerMonthlySummary> result = trainerWorkloadService.getSummary(trainerUsername);

        assertEquals(summary, result.block());
    }
}
