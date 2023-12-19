package com.example.gymcrm.model.requests;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetTrainingsRequest {
    @NotEmpty
    private String username;

    private LocalDate from;
    private LocalDate to;
    private String trainerName;
    private String trainingTypeName;
}
