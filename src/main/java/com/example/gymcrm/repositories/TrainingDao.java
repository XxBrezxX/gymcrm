package com.example.gymcrm.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.gymcrm.model.Trainee;
import com.example.gymcrm.model.Training;

@Repository
public interface TrainingDao extends JpaRepository<Training, Long> {
    List<Training> findByTrainee(Trainee trainee);

}
