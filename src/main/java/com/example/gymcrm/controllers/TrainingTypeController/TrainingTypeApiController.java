package com.example.gymcrm.controllers.TrainingTypeController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.gymcrm.model.TrainingType;
import com.example.gymcrm.services.implementations.models.TrainingTypeServiceImpl;

@Controller
@RequestMapping("/trainingTypes/api")
public class TrainingTypeApiController {

    @Autowired
    private TrainingTypeServiceImpl trainingTypeServiceImpl;

    @GetMapping("/get")
    public ResponseEntity<List<TrainingType>> getTrainingTypes() {
        return ResponseEntity.ok(trainingTypeServiceImpl.getAllTrainingTypes());
    }

}
