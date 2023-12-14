package com.example.gymcrm.services.pureServices;

import java.util.List;

import com.example.gymcrm.model.Trainee;
import com.example.gymcrm.model.User;

public interface TraineeService {
    Trainee createTrainee(Trainee trainee, User user);

    Trainee updateTrainee(Trainee trainee);

    void deleteTrainee(Long id);

    Trainee getTraineeById(Long id);

    List<Trainee> getAllTrainees();

    void deleteAll();

    Trainee getTraineeByUsername(String username);
}
