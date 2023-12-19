package com.example.gymcrm.model.requests;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddTrainingRequest {
    @NotEmpty
    private String trainer;

    @NotEmpty
    private String trainee;

    @NotEmpty
    private String name;

    @NotNull
    private LocalDate date;

    @NotNull
    private Integer duration;
}
