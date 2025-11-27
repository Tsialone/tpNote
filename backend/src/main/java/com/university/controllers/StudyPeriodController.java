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
    public ResponseEntity<ApiResponse<List<StudyPeriodDTO>>> findAll() {
        List<StudyPeriodDTO> liste = this.studyPeriodService.findAll();
        return ResponseEntity.ok(ApiResponse.success(liste));
    }
}
