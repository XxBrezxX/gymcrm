package com.example.gymcrm.model;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Training {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "trainee_id", referencedColumnName = "id")
    private Trainee trainee;

    @ManyToOne
    @JoinColumn(name = "trainer_id", referencedColumnName = "id")
    private Trainer trainer;

    @NonNull
    private String trainingName;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "trainingType_id", referencedColumnName = "id")
    private TrainingType trainingType;

    @NonNull
    private Date trainingDate;

    @NonNull
    private Integer duration;
}
