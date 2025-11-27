package com.university.models;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

import com.university.dtos.StudyPeriodDTO;
import com.university.dtos.YearDTO;

@Entity
@Table(name = "study_period")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudyPeriod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 100)
    private String label;
    
    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;
    
    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "year_id", nullable = false)
    private Year year;

     public static StudyPeriodDTO toDto(StudyPeriod entity) {
        if (entity == null) {
            return null;
        }

        // 1. Convert the nested Year entity first
        YearDTO yearDto = Year.toDto(entity.getYear());
        
        // 2. Build the StudyPeriodDTO using the converted YearDTO
        return new StudyPeriodDTO(
            entity.getId(),
            entity.getLabel(),
            entity.getStartDate(),
            entity.getEndDate(),
            yearDto // Pass the converted DTO
        );
    }
}