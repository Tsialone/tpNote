package com.university.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "option_unit_group")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OptionUnitGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "options_id", nullable = false)
    private Options options;
    
    @Column(columnDefinition = "TEXT")
    private String description;
}