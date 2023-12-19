package com.example.gymcrm.model.requests;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePaswordRequest {
    @NotEmpty
    private String username;

    @NotEmpty
    private String oldPassword;    

    @NotEmpty
    private String newPassword;    
}
