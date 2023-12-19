package com.example.gymcrm.repositories;

import java.util.List;
import java.util.Optional;

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

    Trainer findByUserId(Long id);

    Optional<Trainer> findByUser_Username(String username);

    @Query("SELECT t FROM Trainer t WHERE t NOT IN (SELECT t FROM Trainer t JOIN t.trainees tr WHERE tr.user.isActive = true)")
    List<Trainer> findNotAssignedToActiveTrainee();
}
