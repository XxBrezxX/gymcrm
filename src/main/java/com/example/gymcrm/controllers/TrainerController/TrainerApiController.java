package com.example.gymcrm.controllers.TrainerController;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.gymcrm.model.Trainer;
import com.example.gymcrm.model.TrainingType;
import com.example.gymcrm.model.User;
import com.example.gymcrm.model.requests.RegisterTrainerDto;
import com.example.gymcrm.model.requests.UpdateTrainerRequest;
import com.example.gymcrm.services.implementations.models.TrainerServiceImpl;
import com.example.gymcrm.services.implementations.models.UserServiceImpl;
import com.example.gymcrm.services.pureServices.TrainingTypeService;

@Controller
@RequestMapping("/trainers/api")
public class TrainerApiController {

    @Autowired
    private TrainerServiceImpl trainerServiceImpl;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private TrainingTypeService trainingTypeService;

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> registerUser(@RequestBody RegisterTrainerDto info) {
        User user = new User();
        user.setFirstName(info.getFirstName());
        user.setLastName(info.getLastName());
        user.setIsActive(true);
        user = userServiceImpl.createUser(user);

        TrainingType trainingType = trainingTypeService.getTrainingTypeById(info.getTrainingType());

        Trainer trainer = new Trainer();
        trainer.setTrainingType(trainingType);
        trainer.setUser(user);
        trainer = trainerServiceImpl.createTrainer(trainer);

        Map<String, String> response = new HashMap<>();
        response.put("username", user.getUsername());
        response.put("password", user.getPassword());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/get")
    public ResponseEntity<String> getTrainer(@RequestParam String username) {
        Trainer trainer = trainerServiceImpl.getTrainerByUsername(username);
        return ResponseEntity.ok(trainer.toString());
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateTrainee(@RequestBody UpdateTrainerRequest data) {
        Trainer trainer = trainerServiceImpl.getTrainerByUsername(data.getUsername());

        trainer.getUser().setFirstName(data.getFirstName());
        trainer.getUser().setLastName(data.getLastName());
        trainer.getUser().setIsActive(data.getIsActive());

        TrainingType trainingType = trainingTypeService.getTrainingTypeById(data.getSpecialization());
        trainer.setTrainingType(trainingType);

        trainer = trainerServiceImpl.updateTrainer(trainer);

        return ResponseEntity.ok(trainer.toString());
    }
}
