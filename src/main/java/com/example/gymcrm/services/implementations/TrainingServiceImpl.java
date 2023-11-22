package com.example.gymcrm.services.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gymcrm.model.Training;
import com.example.gymcrm.repositories.TrainingDao;
import com.example.gymcrm.services.TrainingService;

@Service
public class TrainingServiceImpl implements TrainingService {

    @Autowired
    private TrainingDao trainingDao;

    @Override
    public Training createTraining(Training training) {
        return trainingDao.save(training);
    }

    @Override
    public Training getTrainingById(Long id) {
        return trainingDao.getReferenceById(id);
    }

    @Override
    public List<Training> getAllTrainings() {
        return trainingDao.findAll();
    }

}
