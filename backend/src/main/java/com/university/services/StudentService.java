package com.university.services;


import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.university.models.Student;
import com.university.models.User;
import com.university.repositories.StudentRepository;
import com.university.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public Student findByStudentId(Long id) throws Exception{
        return this.studentRepository.findById(id).orElseThrow(() -> new Exception("Student with id = " + id + " not found"));
    }

    public List<Student> getAll() {
        return this.studentRepository.findAll();
    }
}
