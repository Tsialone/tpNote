package com.university.services;

import com.university.dtos.StudentAverageDTO;
import com.university.dtos.StudentAveragesDTO;
import com.university.dtos.StudyPeriodDTO;
import com.university.models.Student;
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
    private final StudentService studentService;
    private final StudyPeriodService studyPeriodService;

    public StudentAveragesDTO[] getAllStudentAverages() throws Exception {
        List<Student> studentList = this.studentService.getAll();
        StudentAveragesDTO[] result = new StudentAveragesDTO[studentList.size()];
         for(int i = 0; i < studentList.size(); i ++) {
            result[i] = this.getStudentAverages(studentList.get(i).getId());
        }
        return result;
    }
    

    public StudentAveragesDTO getStudentAverages(Long studentId) throws Exception {
        List<StudyPeriodDTO> studyPeriodList = this.studyPeriodService.findAll(); 
        Double[] averages = new Double[studyPeriodList.size()];
        
        for(int i = 0; i < studyPeriodList.size(); i ++) {
            averages[i] = this.getStudentAverageScoreByStudyPeriodId(studentId, studyPeriodList.get(i).getId());
        }
        Student student = this.studentService.findByStudentId(studentId);
        StudentAveragesDTO studentAveragesDTO = new StudentAveragesDTO(
            studentId,
            student.getEtu(),
            averages
        );
        return studentAveragesDTO;
    }
    
    public StudentAverageDTO getStudentAverageByStudyPeriodDTO(Long studentId, Long studyPeriodId) throws Exception {
        Double average = this.getStudentAverageScoreByStudyPeriodId(studentId, studyPeriodId);
        Student student = this.studentService.findByStudentId(studentId);
        StudyPeriodDTO studyPeriodDTO = this.studyPeriodService.findById(studyPeriodId);
        StudentAverageDTO dto = new StudentAverageDTO(
                studentId,
                student.getEtu(),
                average,
                studyPeriodDTO
        );
        return dto;

    }

    public Double getStudentAverageScoreByStudyPeriodId(Long studentId, Long studyPeriodId) throws Exception {
        Double average = studentScoreRepository.calculateAverageByStudentIdAndStudyPeriodId(
                studentId, studyPeriodId);
        
        if (average == null) {
            throw new Exception("No score found for student = " + studentId + " and study period = " + studyPeriodId);
        }
        
        return roundToTwoDecimals(average);
    }

   
    public Double getStudentWeightedAverageScoreByStudyPeriodId(Long studentId, Long studyPeriodId) throws Exception {
        Double weightedAverage = studentScoreRepository
                .calculateWeightedAverageByStudentIdAndStudyPeriodId(studentId, studyPeriodId);
        
        if (weightedAverage == null) {
            throw new Exception("No score found for student = " + studentId + " and study period = " + studyPeriodId);
        }
        
        return roundToTwoDecimals(weightedAverage);
    }

    public List<StudentCourseUnitScore> getStudentScoresByStudyPeriodId(Long studentId, Long studyPeriodId) throws Exception {
        List<StudentCourseUnitScore> scores = studentScoreRepository
                .findByStudentIdAndStudyPeriodId(studentId, studyPeriodId);
        
        if (scores.isEmpty()) {
            throw new Exception("No score found for student = " + studentId + " and study period = " + studyPeriodId);
        }
        
        return scores;
    }

    private Double roundToTwoDecimals(Double value) {
        if (value == null) {
            return 0.0;
        }
        return BigDecimal.valueOf(value)
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
    }
}