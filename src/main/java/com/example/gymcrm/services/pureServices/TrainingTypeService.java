package com.example.gymcrm.services.pureServices;

import java.util.List;

import com.example.gymcrm.model.TrainingType;

public interface TrainingTypeService {
    TrainingType createTrainingType(TrainingType trainingType);

    TrainingType updateTrainingType(TrainingType trainingType);

    void deleteTrainingType(Long id);

    TrainingType getTrainingTypeById(Long id);

    List<TrainingType> getAllTrainingTypes();

    void deleteAll();

}
