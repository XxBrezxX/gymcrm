package com.example.gymcrm.model;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TrainerMonthlySummary {
    private String trainerUsername;
    private String trainerFirstName;
    private String trainerLastName;
    private String trainerStatus;
    private Map<Integer, Map<String, Integer>> yearlyTrainingSummary;

}