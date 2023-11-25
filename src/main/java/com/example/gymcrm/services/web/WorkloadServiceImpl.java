package com.example.gymcrm.services.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.gymcrm.model.TrainerMonthlySummary;

import reactor.core.publisher.Mono;

@Service
public class WorkloadServiceImpl implements WorkloadService {
    @Value("${workload.microservice.url}")
    private String workloadUrl;

    @Autowired
    private WebClient webClient;

    @Override
    public void sendWorkload(Object payload) {
        webClient.put()
                .uri(workloadUrl.concat("/modify"))
                .body(Mono.just(payload), payload.getClass())
                .retrieve()
                .toBodilessEntity()
                .onErrorResume(e -> Mono.empty())
                .block();
    }

    @Override
    public Mono<TrainerMonthlySummary> getSummary(String trainerUsername) {
        return webClient.get()
                .uri(uriBuilder -> {
                    return uriBuilder
                            .path(workloadUrl.concat("/workload"))
                            .queryParam("trainerUsername", trainerUsername)
                            .build();
                })
                .retrieve()
                .bodyToMono(TrainerMonthlySummary.class);
    }

}
