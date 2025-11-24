package com.university.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentAverageDTO {
    private Long studentId;
    private Long studyPeriodId;
    private String studyPeriodLabel;
    private Double simpleAverage;
    private Double weightedAverage;
    private Integer totalCourseUnits;
}