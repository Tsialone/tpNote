package com.university.models;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "config_general_average")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConfigGeneralAverage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "min_average", nullable = false, precision = 5, scale = 2)
    private BigDecimal minAverage;
    
    @Column(name = "max_average", nullable = false, precision = 5, scale = 2)
    private BigDecimal maxAverage;
    
    @Column(nullable = false, length = 100)
    private String mention;
    
    @Column(name = "application_date", nullable = false)
    private LocalDate applicationDate;
}