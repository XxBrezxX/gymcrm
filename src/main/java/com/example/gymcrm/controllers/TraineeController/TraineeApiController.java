package com.example.gymcrm.controllers.TraineeController;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.gymcrm.model.Trainee;
import com.example.gymcrm.model.Trainer;
import com.example.gymcrm.model.User;
import com.example.gymcrm.model.requests.DeleteRequest;
import com.example.gymcrm.model.requests.RegisterTraineeDto;
import com.example.gymcrm.model.requests.UpdateTraineesTrainerListRequest;
import com.example.gymcrm.services.implementations.models.TraineeServiceImpl;
import com.example.gymcrm.services.implementations.models.TrainerServiceImpl;
import com.example.gymcrm.services.implementations.models.UserServiceImpl;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/trainees/api")
public class TraineeApiController {

    @Autowired
    private TraineeServiceImpl traineeServiceImpl;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private TrainerServiceImpl trainerServiceImpl;

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> registerUser(@RequestBody RegisterTraineeDto info) {
        User user = new User();
        user.setFirstName(info.getFirstName());
        user.setLastName(info.getLastName());
        user.setIsActive(true);
        user = userServiceImpl.createUser(user);

        Trainee trainee = new Trainee();
        trainee.setAddress(info.getAddress());
        trainee.setDateOfBith(info.getDateOfBirth());
        trainee.setUser(user);
        traineeServiceImpl.createTrainee(trainee);

        Map<String, String> response = new HashMap<>();
        response.put("username", user.getUsername());
        response.put("password", user.getPassword());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/get")
    public ResponseEntity<String> getTrainer(@RequestParam String username) {
        Trainee trainee = traineeServiceImpl.getTraineeByUsername(username);
        return ResponseEntity.ok(trainee.toString());
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateTrainee(@RequestBody Trainee traineeReq) {
        Trainee trainee = traineeServiceImpl.getTraineeByUsername(traineeReq.getUser().getUsername());

        User req = traineeReq.getUser();
        User user = trainee.getUser();

        user.setFirstName(req.getFirstName());
        user.setLastName(req.getLastName());
        user.setIsActive(req.getIsActive());

        User commited = userServiceImpl.updateUser(user);

        trainee.setUser(commited);
        trainee.setDateOfBith(traineeReq.getDateOfBith());
        trainee.setAddress(traineeReq.getAddress());

        Trainee updatedTrainee = traineeServiceImpl.updateTrainee(trainee);
        System.out.println(updatedTrainee);

        return ResponseEntity.ok(updatedTrainee.toString());
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteTrainee(@RequestBody DeleteRequest data) {
        traineeServiceImpl.deleteTraineeByUsername(data.getUsername());
        return ResponseEntity.ok("Borrado exitosamente");
    }

    @PutMapping("/updateList")
    public ResponseEntity<String> updateTrainersList(@RequestBody UpdateTraineesTrainerListRequest data) {
        Trainee trainee = traineeServiceImpl.getTraineeByUsername(data.getUsername());
        Set<Trainer> trainers = new HashSet<>();

        for (String trainer : data.getTrainers()) {
            trainers.add(trainerServiceImpl.getTrainerByUsername(trainer));
        }

        trainee.setTrainers(trainers);
        trainee = traineeServiceImpl.updateTrainee(trainee);

        return ResponseEntity.ok(trainee.toString());
    }

}
