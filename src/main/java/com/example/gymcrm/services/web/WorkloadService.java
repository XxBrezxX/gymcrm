package com.example.gymcrm.services.web;

import com.example.gymcrm.model.TrainerMonthlySummary;

import reactor.core.publisher.Mono;

public interface WorkloadService {
    void sendWorkload(Object payload);

    Mono<TrainerMonthlySummary> getSummary(String trainerUsername);
}