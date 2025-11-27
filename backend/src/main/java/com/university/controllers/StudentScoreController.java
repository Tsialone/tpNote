package com.university.controllers;

import com.university.api.ApiResponse;
import com.university.api.ApiError;
import com.university.dtos.StudentAverageDTO;
import com.university.dtos.StudentAveragesDTO;
import com.university.dtos.StudyPeriodDTO;
import com.university.models.Student;
import com.university.models.StudentCourseUnitScore;
import com.university.models.StudyPeriod;
import com.university.services.StudentScoreService;
import com.university.services.StudentService;
import com.university.services.StudyPeriodService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/studentScores")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class StudentScoreController {

    private final StudentScoreService studentScoreService;

    @GetMapping("/study-periods/averages")
    public ResponseEntity<ApiResponse<StudentAveragesDTO[]>> getStudentAverageForAllSemesters() throws Exception {
        StudentAveragesDTO[] studentAveragesDTOs = this.studentScoreService.getAllStudentAverages();
        return ResponseEntity.ok(ApiResponse.success(studentAveragesDTOs));
    }

    @GetMapping("/{studentId}/study-periods/averages")
    public ResponseEntity<ApiResponse<StudentAveragesDTO>> getStudentAverageForAllSemesters(
            @PathVariable Long studentId) throws Exception {
        
        StudentAveragesDTO studentAveragesDTO = this.studentScoreService.getStudentAverages(studentId);
        return ResponseEntity.ok(ApiResponse.success(studentAveragesDTO));
    }
    
    @GetMapping("/{studentId}/study-periods/{studyPeriodId}/average")
    public ResponseEntity<ApiResponse<StudentAverageDTO>> getStudentAverageByStudyPeriod(
            @PathVariable Long studentId,
            @PathVariable Long studyPeriodId) throws Exception {
        StudentAverageDTO dto = this.studentScoreService.getStudentAverageByStudyPeriodDTO(studentId, studyPeriodId);
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
