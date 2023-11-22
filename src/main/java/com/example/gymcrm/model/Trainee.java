package com.example.gymcrm.model;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Trainee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date dateOfBith;

    private String address;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany
    @JoinTable(name = "trainee_trainer", joinColumns = @JoinColumn(name = "trainee_id"), inverseJoinColumns = @JoinColumn(name = "trainer_id"))
    private Set<Trainer> trainers = new HashSet<>();
}
