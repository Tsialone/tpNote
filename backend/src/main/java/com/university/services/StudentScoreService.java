package com.university.services;

import com.university.models.StudentCourseUnitScore;
import com.university.repositories.StudentCourseUnitScoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StudentScoreService {

    private final StudentCourseUnitScoreRepository studentScoreRepository;

    /**
     * Get student's average score for a specific study period (simple average)
     * @param studentId the student ID
     * @param studyPeriodId the study period ID
     * @return average score rounded to 2 decimal places
     * @throws Exception 
     * @throws NoScoresFoundException if no scores found for the student in the study period
     */
    public Double getStudentAverageScoreByStudyPeriodId(Long studentId, Long studyPeriodId) throws Exception {
        Double average = studentScoreRepository.calculateAverageByStudentIdAndStudyPeriodId(
                studentId, studyPeriodId);
        
        if (average == null) {
            throw new Exception("No score found for student = " + studentId + " and study period = " + studyPeriodId);
        }
        
        return roundToTwoDecimals(average);
    }

    /**
     * Get student's weighted average score for a specific study period 
     * (weighted by course unit credits)
     * @param studentId the student ID
     * @param studyPeriodId the study period ID
     * @return weighted average score rounded to 2 decimal places
     * @throws Exception 
     * @throws NoScoresFoundException if no scores found for the student in the study period
     */
    public Double getStudentWeightedAverageScoreByStudyPeriodId(Long studentId, Long studyPeriodId) throws Exception {
        Double weightedAverage = studentScoreRepository
                .calculateWeightedAverageByStudentIdAndStudyPeriodId(studentId, studyPeriodId);
        
        if (weightedAverage == null) {
            throw new Exception("No score found for student = " + studentId + " and study period = " + studyPeriodId);
        }
        
        return roundToTwoDecimals(weightedAverage);
    }

    /**
     * Get all scores for a student in a specific study period
     * @param studentId the student ID
     * @param studyPeriodId the study period ID
     * @return list of student course unit scores
     * @throws Exception 
     * @throws NoScoresFoundException if no scores found for the student in the study period
     */
    public List<StudentCourseUnitScore> getStudentScoresByStudyPeriodId(Long studentId, Long studyPeriodId) throws Exception {
        List<StudentCourseUnitScore> scores = studentScoreRepository
                .findByStudentIdAndStudyPeriodId(studentId, studyPeriodId);
        
        if (scores.isEmpty()) {
            throw new Exception("No score found for student = " + studentId + " and study period = " + studyPeriodId);
        }
        
        return scores;
    }

    /**
     * Round a double value to 2 decimal places
     * @param value the value to round
     * @return rounded value
     */
    private Double roundToTwoDecimals(Double value) {
        if (value == null) {
            return 0.0;
        }
        return BigDecimal.valueOf(value)
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
    }
}