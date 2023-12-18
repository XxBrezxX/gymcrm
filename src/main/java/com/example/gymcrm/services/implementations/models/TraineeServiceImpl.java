package com.example.gymcrm.services.implementations.models;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.gymcrm.model.Trainee;
import com.example.gymcrm.repositories.TraineeDao;
import com.example.gymcrm.services.pureServices.TraineeService;

@Service
public class TraineeServiceImpl implements TraineeService {

    @Autowired
    private TraineeDao traineeDao;

    @Override
    public Trainee createTrainee(Trainee trainee) {
        return traineeDao.save(trainee);
    }

    @Override
    public Trainee updateTrainee(Trainee trainee) {
        return traineeDao.save(trainee);
    }

    @Override
    public void deleteTrainee(Long id) {
        traineeDao.deleteById(id);
    }

    @Override
    public Trainee getTraineeById(Long id) {
        return traineeDao.getReferenceById(id);
    }

    @Override
    public List<Trainee> getAllTrainees() {
        return traineeDao.findAll();
    }

    @Override
    public void deleteAll() {
        traineeDao.deleteAll();
    }

    @Override
    public Trainee getTraineeByUsername(String username) {
        return traineeDao.findByUser_Username(username)
                .orElseThrow(() -> new RuntimeException("No se encontró el Trainer con el username: " + username));
    }

    @Transactional
    @Override
    public void deleteTraineeByUsername(String username) {
        traineeDao.deleteTraineeByUser_Username(username);
    }

}
