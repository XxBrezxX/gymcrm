package com.example.gymcrm.model;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TrainerWorkloadRequest {

    @NotNull
    @NotEmpty
    private String trainerUsername;

    @NotNull
    @NotEmpty
    private String trainerFirstName;

    @NotNull
    @NotEmpty
    private String trainerLastName;

    @NotNull
    private boolean isActive;

    @NotNull
    private LocalDate trainingDate;

    @NotNull
    private int trainingDuration;

    @NotNull
    private ActionType actionType;

    public enum ActionType {
        ADD,
        DELETE
    }
}