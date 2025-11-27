package com.university.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "program")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Program {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String label;
    
    @Column(columnDefinition = "TEXT")
    private String description;
}