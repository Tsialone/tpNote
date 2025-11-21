package com.university.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "option_course_unit_period",
    uniqueConstraints = @UniqueConstraint(
        name = "uk_period_option_unit",
        columnNames = {"study_period_id", "options_id", "course_unit_id"}
    ))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OptionCourseUnitPeriod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "study_period_id", nullable = false)
    private StudyPeriod studyPeriod;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "options_id", nullable = false)
    private Options options;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_unit_id", nullable = false)
    private CourseUnit courseUnit;
}