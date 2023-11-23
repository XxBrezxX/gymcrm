package com.example.gymcrm.services.implementations.models;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gymcrm.model.Trainer;
import com.example.gymcrm.model.User;
import com.example.gymcrm.repositories.TrainerDao;
import com.example.gymcrm.services.pureServices.TrainerService;

@Service
public class TrainerServiceImpl implements TrainerService {

    @Autowired
    private TrainerDao trainerDao;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Override
    public Trainer createTrainer(Trainer trainer, User user) {
        userServiceImpl.createUser(user);
        trainer.setUser(user);
        return trainerDao.save(trainer);
    }

    @Override
    public Trainer updateTrainer(Trainer trainer) {
        return trainerDao.save(trainer);
    }

    @Override
    public Trainer getTrainerById(Long id) {
        return trainerDao.getReferenceById(id);
    }

    @Override
    public List<Trainer> getAllTrainers() {
        return trainerDao.findAll();
    }

    @Override
    public void deleteAll() {
        trainerDao.deleteAll();
    }

    public void deleteAllTraineeTrainerRelations() {
        trainerDao.deleteAllTraineeTrainerRelations();
    }
}
