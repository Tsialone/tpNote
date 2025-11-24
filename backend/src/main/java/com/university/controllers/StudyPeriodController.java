package com.university.controllers;

import com.university.api.ApiResponse;
import com.university.api.ApiError;
import com.university.dtos.StudentAverageDTO;
import com.university.dtos.StudyPeriodDTO;
import com.university.models.StudentCourseUnitScore;
import com.university.models.StudyPeriod;
import com.university.services.StudentScoreService;
import com.university.services.StudyPeriodService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/study-periods")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class StudyPeriodController {

    private final StudyPeriodService studyPeriodService;

    @GetMapping()
    public ResponseEntity<ApiResponse<StudentAverageDTO>> findAll() {
        List<StudyPeriod> liste = this.studyPeriodService.findAll();
        if (liste.isEmpty() || liste == null) {
            // No content but still return consistent ApiResponse
            return ResponseEntity.status(204).body(ApiResponse.error(new ApiError("NO_STUDY_PERIOD_FOUND", "No Study period Semester found", "No Study period Semester found")));
        }

        StudyPeriodDTO dto = new StudyPeriodDTO(
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
