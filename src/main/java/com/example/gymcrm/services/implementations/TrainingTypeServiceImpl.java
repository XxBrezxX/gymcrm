package com.example.gymcrm.services.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gymcrm.model.TrainingType;
import com.example.gymcrm.repositories.TrainingTypeDao;
import com.example.gymcrm.services.TrainingTypeService;

@Service
public class TrainingTypeServiceImpl implements TrainingTypeService {

    @Autowired
    TrainingTypeDao trainingTypeDao;

    @Override
    public TrainingType createTrainingType(TrainingType trainingType) {
        return trainingTypeDao.save(trainingType);
    }

    @Override
    public TrainingType updateTrainingType(TrainingType trainingType) {
        return trainingTypeDao.save(trainingType);
    }

    @Override
    public void deleteTrainingType(Long id) {
        trainingTypeDao.deleteById(id);
    }

    @Override
    public TrainingType getTrainingTypeById(Long id) {
        return trainingTypeDao.getReferenceById(id);
    }

    @Override
    public List<TrainingType> getAllTrainingTypes() {
        return trainingTypeDao.findAll();
    }

    @Override
    public void deleteAll() {
        trainingTypeDao.deleteAll();
    }

}
