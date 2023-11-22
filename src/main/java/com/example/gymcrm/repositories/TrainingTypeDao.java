package com.example.gymcrm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gymcrm.model.TrainingType;

public interface TrainingTypeDao extends JpaRepository<TrainingType, Long> {
    
}
