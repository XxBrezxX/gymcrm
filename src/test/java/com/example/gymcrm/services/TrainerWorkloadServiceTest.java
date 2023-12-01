package com.example.gymcrm.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.gymcrm.model.TrainerMonthlySummary;
import com.example.gymcrm.model.TrainerWorkloadRequest;
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
    void testSendWorkload() {
        TrainerWorkloadRequest request = new TrainerWorkloadRequest();
        request.setActive(true);

        trainerWorkloadService.updateWorkload(request);

        verify(workloadServiceImpl, atLeastOnce()).sendWorkload(request);
    }

    @Test
    void testGetSummary() {
        TrainerMonthlySummary trainerMonthlySummary = new TrainerMonthlySummary();

        when(workloadServiceImpl.getSummary("user")).thenReturn(Mono.just(trainerMonthlySummary));

        Mono<TrainerMonthlySummary> response = trainerWorkloadService.getSummary("user");

        assertEquals(trainerMonthlySummary, response.block());
    }
}
