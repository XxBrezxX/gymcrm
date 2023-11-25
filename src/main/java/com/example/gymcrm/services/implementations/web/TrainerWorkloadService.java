package com.example.gymcrm.services.implementations.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gymcrm.model.TrainerMonthlySummary;
import com.example.gymcrm.model.TrainerWorkloadRequest;
import com.example.gymcrm.services.web.WorkloadServiceImpl;

import reactor.core.publisher.Mono;

@Service
public class TrainerWorkloadService {
    @Autowired
    private WorkloadServiceImpl workloadServiceImpl;

    public void updateWorkload(TrainerWorkloadRequest request) {
        workloadServiceImpl.sendWorkload(request);
    }

    public Mono<TrainerMonthlySummary> getSummary(String trainerUsername) {
        return workloadServiceImpl.getSummary(trainerUsername);
    }
}
