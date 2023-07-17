package com.example.spring_first_app.service;

import com.example.spring_first_app.dto.StudentDTO;

import java.util.ArrayList;

public interface StudentService {
    ArrayList<StudentDTO> getAllStudents();

    StudentDTO getStudentById(int id);

    StudentDTO insertStudent(StudentDTO studentDTO);

    StudentDTO updateStudentById(int id, StudentDTO studentDTO);

    boolean deleteStudentById(int id);
}
