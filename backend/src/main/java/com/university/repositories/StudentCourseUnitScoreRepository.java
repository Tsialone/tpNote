package com.university.repositories;

import com.university.models.StudentCourseUnitScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentCourseUnitScoreRepository extends JpaRepository<StudentCourseUnitScore, Long> {
    
    /**
     * Get all scores for a student in a specific study period
     * @param studentId the student ID
     * @param studyPeriodId the study period ID
     * @return list of scores
     */
    @Query("SELECT s FROM StudentCourseUnitScore s " +
           "WHERE s.student.id = :studentId " +
           "AND s.examSession.studyPeriod.id = :studyPeriodId")
    List<StudentCourseUnitScore> findByStudentIdAndStudyPeriodId(
            @Param("studentId") Long studentId,
            @Param("studyPeriodId") Long studyPeriodId);
    
    /**
     * Calculate average score for a student in a specific study period
     * @param studentId the student ID
     * @param studyPeriodId the study period ID
     * @return average score as Double (null if no scores)
     */
    @Query("SELECT AVG(s.score) FROM StudentCourseUnitScore s " +
           "WHERE s.student.id = :studentId " +
           "AND s.examSession.studyPeriod.id = :studyPeriodId")
    Double calculateAverageByStudentIdAndStudyPeriodId(
            @Param("studentId") Long studentId,
            @Param("studyPeriodId") Long studyPeriodId);
    
    /**
     * Calculate weighted average score for a student in a specific study period
     * (weighted by course unit credits)
     * @param studentId the student ID
     * @param studyPeriodId the study period ID
     * @return weighted average score as Double (null if no scores)
     */
    @Query("SELECT SUM(s.score * s.courseUnit.credits) / SUM(s.courseUnit.credits) " +
           "FROM StudentCourseUnitScore s " +
           "WHERE s.student.id = :studentId " +
           "AND s.examSession.studyPeriod.id = :studyPeriodId")
    Double calculateWeightedAverageByStudentIdAndStudyPeriodId(
            @Param("studentId") Long studentId,
            @Param("studyPeriodId") Long studyPeriodId);
}