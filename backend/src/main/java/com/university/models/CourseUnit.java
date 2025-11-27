package com.university.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "course_unit")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseUnit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true, nullable = false, length = 20)
    private String code;
    
    @Column(nullable = false)
    private String label;
    
    @Column(columnDefinition = "TEXT")
    private String description;
    
    @Column(nullable = false)
    private Integer credits;
}