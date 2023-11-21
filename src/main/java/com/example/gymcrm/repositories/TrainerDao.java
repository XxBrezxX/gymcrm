package com.example.gymcrm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.gymcrm.model.Trainer;

@Repository
public interface TrainerDao extends JpaRepository<Trainer, Long> {

}
