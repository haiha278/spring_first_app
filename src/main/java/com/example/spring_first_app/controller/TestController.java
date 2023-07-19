package com.example.spring_first_app.controller;

import com.example.spring_first_app.dto.StudentDTO;
import com.example.spring_first_app.entity.Student;
import com.example.spring_first_app.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
public class TestController {
    @Autowired
    private StudentService studentService;

    @GetMapping("/studentList")
    public ResponseEntity<Object> getAllStudent() {
        ArrayList<StudentDTO> students = studentService.getAllStudents();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping("/findStudentById/{id}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable("id") Integer studentId) {
        StudentDTO studentDTO = studentService.getStudentById(studentId);
        return new ResponseEntity<>(studentDTO, HttpStatus.OK);
    }

    @PostMapping("/createStudent")
    public ResponseEntity<Object> createStudent(@RequestBody @Validated StudentDTO studentDTO) {
        StudentDTO studentDTO1 = studentService.insertStudent(studentDTO);
        return new ResponseEntity<>(studentDTO1, HttpStatus.CREATED);
    }

    @PutMapping("/updateStudent/{id}")
    public ResponseEntity<Object> updateStudent(@RequestBody @Validated StudentDTO studentDTO, @PathVariable Integer id) {
        StudentDTO studentDTO1 = studentService.updateStudentById(id, studentDTO);
        return new ResponseEntity<>(studentDTO1, HttpStatus.OK);
    }

    @DeleteMapping("/deleteStudent/{id}")
    public Map<String, Boolean> deleteStudent(@PathVariable Integer id) {
        Map<String, Boolean> response = new HashMap<>();
        boolean check = studentService.deleteStudentById(id);
        response.put("deleted", check);
        return response;
    }
}
