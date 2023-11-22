package com.example.gymcrm.services;

import java.util.List;

import com.example.gymcrm.model.Trainee;

public interface TraineeService {
    Trainee createTrainee(Trainee trainee);

    Trainee updateTrainee(Trainee trainee);

    void deleteTrainee(Long id);

    Trainee getTraineeById(Long id);

    List<Trainee> getAllTrainees();
}
