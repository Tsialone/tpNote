package com.university.models;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "config_result_grade")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConfigResultGrade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "min_score", nullable = false, precision = 5, scale = 2)
    private BigDecimal minScore;
    
    @Column(name = "max_score", nullable = false, precision = 5, scale = 2)
    private BigDecimal maxScore;
    
    @Column(nullable = false, length = 100)
    private String label;
    
    @Column(name = "application_date", nullable = false)
    private LocalDate applicationDate;
}