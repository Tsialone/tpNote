package com.university.models;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "config_progression")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConfigProgression {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "min_general_average", nullable = false, precision = 5, scale = 2)
    private BigDecimal minGeneralAverage;
    
    @Column(name = "eliminatory_score", nullable = false, precision = 5, scale = 2)
    private BigDecimal eliminatoryScore;
    
    @Column(name = "max_eliminatory_unit", nullable = false)
    private Integer maxEliminatoryUnit;
    
    @Column(name = "application_date", nullable = false)
    private LocalDate applicationDate;
}