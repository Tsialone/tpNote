package com.university.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentAverageDTO {
    private Long studentId;
    private String etu;
    private Double average;
    private StudyPeriodDTO stydyPeriodDTO;
}