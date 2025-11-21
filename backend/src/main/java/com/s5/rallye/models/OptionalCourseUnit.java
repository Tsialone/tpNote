package com.university.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "optional_course_unit",
    uniqueConstraints = @UniqueConstraint(
        name = "uk_unit_group",
        columnNames = {"course_unit_id", "option_unit_group_id"}
    ))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OptionalCourseUnit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_unit_id", nullable = false)
    private CourseUnit courseUnit;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "option_unit_group_id", nullable = false)
    private OptionUnitGroup optionUnitGroup;
}