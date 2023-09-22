package com.example.spring_first_app.controller;

import com.example.spring_first_app.dto.LoginDTO;
import com.example.spring_first_app.dto.StudentDTO;
import com.example.spring_first_app.dto.UserDTO;
import com.example.spring_first_app.service.StudentService;
import com.example.spring_first_app.service.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@org.springframework.web.bind.annotation.RestController
public class RestController {
    @Autowired
    private UserServiceImp userServiceImp;
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
    public ResponseEntity<Object> deleteStudent(@PathVariable Integer id) {
        boolean check = studentService.deleteStudentById(id);
        return new ResponseEntity<>(check, HttpStatus.ACCEPTED);
    }

    @PostMapping("/create-user")
    public ResponseEntity<Object> createUser(@RequestBody UserDTO userDTO) {
        UserDTO user = userServiceImp.createUser(userDTO);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<LoginDTO> login(@RequestBody UserDTO userDTO){
        var result = userServiceImp.login(userDTO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<LoginDTO> refresh(@RequestHeader(value = "refresh-token") String refreshToken){
        var result = userServiceImp.refresh(refreshToken);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
