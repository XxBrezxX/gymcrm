package com.example.gymcrm.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.gymcrm.model.Trainer;

@Repository
public interface TrainerDao extends JpaRepository<Trainer, Long> {
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM trainee_trainer", nativeQuery = true)
    void deleteAllTraineeTrainerRelations();
}
