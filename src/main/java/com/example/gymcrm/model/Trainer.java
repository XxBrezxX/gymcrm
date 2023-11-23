package com.example.gymcrm.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Trainer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "training_type_id", referencedColumnName = "id")
    private TrainingType trainingType;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToMany(mappedBy = "trainers")
    private Set<Trainee> trainees = new HashSet<>();
}
