package com.university.controllers;

import com.university.api.ApiResponse;
import com.university.api.ApiError;
import com.university.dtos.StudentAverageDTO;
import com.university.models.StudentCourseUnitScore;
import com.university.services.StudentScoreService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class StudentScoreController {

    private final StudentScoreService studentScoreService;

    @GetMapping("/{studentId}/study-periods/{studyPeriodId}/average")
    public ResponseEntity<ApiResponse<StudentAverageDTO>> getStudentAverageByStudyPeriod(
            @PathVariable Long studentId,
            @PathVariable Long studyPeriodId) throws Exception {

        // Service may throw EntityNotFoundException → handled by GlobalExceptionHandler
        Double simpleAverage = studentScoreService
                .getStudentAverageScoreByStudyPeriodId(studentId, studyPeriodId);

        Double weightedAverage = studentScoreService
                .getStudentWeightedAverageScoreByStudyPeriodId(studentId, studyPeriodId);

        List<StudentCourseUnitScore> scores = studentScoreService
                .getStudentScoresByStudyPeriodId(studentId, studyPeriodId);

        if (scores.isEmpty()) {
            // No content but still return consistent ApiResponse
            return ResponseEntity.status(204).body(ApiResponse.error(new ApiError("NO_SCORE_FOUND", "No scores found", "No score found for the student = " + studentId)));
        }

        StudentAverageDTO dto = new StudentAverageDTO(
                studentId,
                studyPeriodId,
                scores.get(0).getExamSession().getStudyPeriod().getLabel(),
                simpleAverage,
                weightedAverage,
                scores.size()
        );

        return ResponseEntity.ok(ApiResponse.success(dto));
    }


    @GetMapping("/{studentId}/study-periods/{studyPeriodId}/scores")
    public ResponseEntity<ApiResponse<List<StudentCourseUnitScore>>> getStudentScoresByStudyPeriod(
            @PathVariable Long studentId,
            @PathVariable Long studyPeriodId) throws Exception {

        // Service may throw EntityNotFoundException → handled globally
        List<StudentCourseUnitScore> scores = studentScoreService
                .getStudentScoresByStudyPeriodId(studentId, studyPeriodId);

        if (scores.isEmpty()) {
            // No content but still return consistent ApiResponse
            return ResponseEntity.status(204).body(ApiResponse.error(new ApiError("NO_SCORE_FOUND", "No scores found", "No score found for the student = " + studentId)));
        }

        return ResponseEntity.ok(ApiResponse.success(scores));
    }
}
