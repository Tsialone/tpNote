package com.university.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "study_period_course_unit",
    uniqueConstraints = @UniqueConstraint(
        name = "uk_period_unit",
        columnNames = {"study_period_id", "course_unit_id"}
    ))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudyPeriodCourseUnit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "study_period_id", nullable = false)
    private StudyPeriod studyPeriod;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_unit_id", nullable = false)
    private CourseUnit courseUnit;
}