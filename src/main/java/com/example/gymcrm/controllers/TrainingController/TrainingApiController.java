package com.example.gymcrm.controllers.TrainingController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.gymcrm.model.Trainee;
import com.example.gymcrm.model.Trainer;
import com.example.gymcrm.model.Training;
import com.example.gymcrm.model.requests.AddTrainingRequest;
import com.example.gymcrm.model.requests.GetTrainingsRequest;
import com.example.gymcrm.services.implementations.models.TraineeServiceImpl;
import com.example.gymcrm.services.implementations.models.TrainerServiceImpl;
import com.example.gymcrm.services.implementations.models.TrainingServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/trainings/api")
public class TrainingApiController {

    @Autowired
    private TrainingServiceImpl trainingServiceImpl;
    @Autowired
    private TraineeServiceImpl traineeServiceImpl;
    @Autowired
    private TrainerServiceImpl trainerServiceImpl;

    @GetMapping("/getTrainings")
    public ResponseEntity<String> getTrainings(@RequestBody GetTrainingsRequest data) {
        List<Training> trainings = trainingServiceImpl.getAllTrainingsByUsername(data.getUsername());

        Stream<Training> stream = trainings.stream();

        if (data.getFrom() != null) {
            stream = stream.filter(training -> !training.getTrainingDate().isBefore(data.getFrom()));
        }
        if (data.getTo() != null) {
            stream = stream.filter(training -> !training.getTrainingDate().isAfter(data.getTo()));
        }
        if (data.getTrainerName() != null) {
            stream = stream.filter(
                    training -> training.getTrainer().getUser().getFirstName().contentEquals(data.getTrainerName()));
        }
        if (data.getTrainingTypeName() != null) {
            stream = stream.filter(training -> training.getTrainingType().getTrainingTypeName()
                    .contentEquals(data.getTrainingTypeName()));
        } 
        List<Training> response = stream.collect(Collectors.toList());
        return ResponseEntity.ok(response.toString());
    }

    @PostMapping("/add")
    public ResponseEntity<String> createTraining(@RequestBody AddTrainingRequest data) {
        Trainee trainee = traineeServiceImpl.getTraineeByUsername(data.getTrainee());
        Trainer trainer = trainerServiceImpl.getTrainerByUsername(data.getTrainer());

        Training training = new Training();
        training.setTrainee(trainee);
        training.setTrainer(trainer);
        training.setTrainingName(data.getName());
        training.setTrainingDate(data.getDate());
        training.setDuration(data.getDuration());
        trainingServiceImpl.createTraining(training);

        return ResponseEntity.ok("OK");
    }

}
