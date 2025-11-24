package com.university.services;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.university.dtos.StudyPeriodDTO;
import com.university.dtos.YearDTO;
import com.university.models.StudyPeriod;
import com.university.models.User;
import com.university.repositories.StudentRepository;
import com.university.repositories.StudyPeriodRepository;
import com.university.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudyPeriodService {
    private final StudyPeriodRepository studyPeriodRepository ;

    public List<StudyPeriodDTO> findAll() {
        List<StudyPeriod> list = studyPeriodRepository.findAll();
        List<StudyPeriod> result = new ArrayList<>();
        for(int i = 0; i < 4; i ++) {
            result.add(list.get(i));
        }
        return result.stream()
                       .map(StudyPeriod::toDto)
                       .collect(Collectors.toList());
    }

   

}
