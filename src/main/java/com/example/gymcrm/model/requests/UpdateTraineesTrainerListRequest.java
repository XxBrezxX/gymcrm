package com.example.gymcrm.model.requests;

import java.util.List;

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
public class UpdateTraineesTrainerListRequest {
    @NotEmpty
    private String username;

    @NotNull
    private List<String> trainers;
}
