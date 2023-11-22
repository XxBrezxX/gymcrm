package com.example.gymcrm.services;

import java.util.List;

import com.example.gymcrm.model.Trainer;

public interface TrainerService {
    Trainer createTrainer(Trainer trainer);

    Trainer updateTrainer(Trainer trainer);

    Trainer getTrainerById(Long id);

    List<Trainer> getAllTrainers();

    void deleteAll();
}
