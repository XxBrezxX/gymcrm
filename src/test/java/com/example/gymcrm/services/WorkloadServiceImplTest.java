package com.example.gymcrm.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.gymcrm.model.TrainerMonthlySummary;
import com.example.gymcrm.services.web.WorkloadServiceImpl;
import com.google.common.net.HttpHeaders;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@DataJpaTest
public class WorkloadServiceImplTest {

    @InjectMocks
    private WorkloadServiceImpl workloadServiceImpl;

    @Test
    void testGetSummary() {
        // Configura el WebClient simulado con un objeto de respuesta simulado
        WebClient webClient = WebClient.builder()
                .exchangeFunction(clientRequest -> {
                    return Mono.just(ClientResponse.create(HttpStatus.OK)
                            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                            .body("{ \"trainerName\": \"John Doe\"}")
                            .build());
                }).build();

        MockitoAnnotations.openMocks(webClient);
        // Llama al método getSummary para recuperar el resumen
        Mono<TrainerMonthlySummary> result = workloadServiceImpl.getSummary("JohnDoe");

        // Verifica que el resumen no es nulo
        assertNotNull(result);

        // Verifica que el resumen tenga los valores esperados
        StepVerifier.create(result.map(summary -> {
            assertEquals("John Doe", summary.getTrainerFirstName());
            return summary;
        })).expectNextCount(1).verifyComplete();
    }
}
