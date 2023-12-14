package com.example.gymcrm.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.gymcrm.model.Trainee;

@Repository
public interface TraineeDao extends JpaRepository<Trainee, Long> {

    Trainee findByUserId(Long id);

    Optional<Trainee> findByUser_Username(String username);
}
