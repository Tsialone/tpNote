package com.university.models;

import com.university.dtos.YearDTO;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "year")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Year {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 50)
    private String label;

    public static YearDTO toDto(Year yearEntity) {
        if (yearEntity == null) {
            return null;
        }
        return new YearDTO(
            yearEntity.getId(),
            yearEntity.getLabel()
        );
    }
}