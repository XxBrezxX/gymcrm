package com.example.gymcrm.services.implementations.models;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gymcrm.model.Trainee;
import com.example.gymcrm.model.Trainer;
import com.example.gymcrm.model.TrainerWorkloadRequest;
import com.example.gymcrm.model.Training;
import com.example.gymcrm.model.User;
import com.example.gymcrm.model.TrainerWorkloadRequest.ActionType;
import com.example.gymcrm.repositories.TrainingDao;
import com.example.gymcrm.services.implementations.web.TrainerWorkloadService;
import com.example.gymcrm.services.pureServices.TrainingService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class TrainingServiceImpl implements TrainingService {

    @Autowired
    private TrainingDao trainingDao;

    @Autowired
    private TrainerWorkloadService trainerWorkloadService;

    @HystrixCommand
    @Override
    public Training createTraining(Training training) {
        TrainerWorkloadRequest tWorkloadRequest = new TrainerWorkloadRequest();
        Trainer trainer = training.getTrainer();
        User trainerUser = trainer.getUser();

        tWorkloadRequest.setTrainerUsername(trainerUser.getUsername());
        tWorkloadRequest.setTrainerFirstName(trainerUser.getFirstName());
        tWorkloadRequest.setTrainerLastName(trainerUser.getLastName());
        tWorkloadRequest.setActive(trainerUser.getIsActive());
        tWorkloadRequest.setTrainingDate(training.getTrainingDate());
        tWorkloadRequest.setTrainingDuration(training.getDuration());
        tWorkloadRequest.setActionType(ActionType.ADD);

        trainerWorkloadService.updateWorkload(tWorkloadRequest);

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

    public void deleteAll() {
        trainingDao.deleteAll();
    }

    @Override
    public void deleteTrainingsByTrainee(Trainee trainee) {
        List<Training> trainings = trainingDao.findByTrainee(trainee);
        for (Training training : trainings) {
            training.setTrainee(null); // cambia la referencia a null
            trainingDao.save(training); // guarda los cambios
        }
    }

    @Override
    public List<Training> getAllTrainingsByUsername(String username) {
        return trainingDao.findByTrainee_User_Username(username);
    }

}
