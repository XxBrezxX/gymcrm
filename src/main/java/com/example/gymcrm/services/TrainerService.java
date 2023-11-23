package com.example.gymcrm.services;

import java.util.List;

import com.example.gymcrm.model.Trainer;
import com.example.gymcrm.model.User;

public interface TrainerService {
    Trainer createTrainer(Trainer trainer, User user);

    Trainer updateTrainer(Trainer trainer);

    Trainer getTrainerById(Long id);

    List<Trainer> getAllTrainers();

    void deleteAll();
}
