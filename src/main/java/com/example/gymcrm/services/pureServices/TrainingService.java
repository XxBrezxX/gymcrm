package com.example.gymcrm.services.pureServices;

import java.util.List;

import com.example.gymcrm.model.Trainee;
import com.example.gymcrm.model.Training;

public interface TrainingService {

    Training createTraining(Training training);

    Training getTrainingById(Long id);

    List<Training> getAllTrainings();

    void deleteTrainingsByTrainee(Trainee trainee);
}
