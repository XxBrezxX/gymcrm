package com.example.gymcrm.model.requests;

import java.sql.Date;

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
public class RegisterTraineeDto {
    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @NotNull
    private Date dateOfBirth;

    @NotEmpty
    private String address;
}
