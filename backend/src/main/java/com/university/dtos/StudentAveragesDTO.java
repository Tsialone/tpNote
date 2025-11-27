package com.university.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentAveragesDTO {
    private Long studentId;
    private String etu;
    private Double[] averages;
}