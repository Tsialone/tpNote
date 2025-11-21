package com.university.models;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "registration", 
    uniqueConstraints = @UniqueConstraint(
        name = "uk_student_period_year",
        columnNames = {"student_id", "study_period_id", "academic_year_id"}
    ))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Registration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "registration_date", nullable = false)
    private LocalDate registrationDate;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "study_period_id", nullable = false)
    private StudyPeriod studyPeriod;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "academic_year_id", nullable = false)
    private AcademicYear academicYear;
}