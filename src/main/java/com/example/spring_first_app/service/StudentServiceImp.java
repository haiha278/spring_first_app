package com.example.spring_first_app.service;

import com.example.spring_first_app.dto.StudentDTO;
import com.example.spring_first_app.entity.Student;
import com.example.spring_first_app.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Repository
public class StudentServiceImp implements StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public ArrayList<StudentDTO> getAllStudents() {
        ArrayList<StudentDTO> studentDTOS = new ArrayList<>();
        List<Student> studentList = studentRepository.findAll();
        for (Student student : studentList) {
            StudentDTO studentDTO = new StudentDTO();
            studentDTO.setId(student.getId());
            studentDTO.setName(student.getName());
            studentDTO.setAge(student.getAge());
            studentDTO.setGpa(student.getGpa());
            studentDTOS.add(studentDTO);
        }
        return studentDTOS;
    }

    @Override
    public StudentDTO getStudentById(int id) {
        Student student = new Student();
        StudentDTO studentDTO = new StudentDTO();
        try {
            student = studentRepository.getStudentById(id);
        } catch (Exception e) {
        } finally {
            if (student != null) {
                studentDTO.setId(student.getId());
                studentDTO.setName(student.getName());
                studentDTO.setAge(student.getAge());
                studentDTO.setGpa(student.getGpa());
            }
        }
        return studentDTO;
    }

    @Override
    public StudentDTO insertStudent(StudentDTO studentDTO) {
        Student student = new Student();
        student.setName(studentDTO.getName());
        student.setAge(studentDTO.getAge());
        student.setGpa(studentDTO.getGpa());
        studentRepository.save(student);
        studentDTO.setId(student.getId());
        return studentDTO;
    }

    @Override
    public StudentDTO updateStudentById(int id, StudentDTO studentDTO) {
        Student student = new Student();
        try {
            student = studentRepository.getStudentById(id);
        } catch (Exception e) {

        } finally {
            if (student != null) {
                if (!studentDTO.getName().equals(null)) {
                    student.setName(studentDTO.getName());
                }
                if (studentDTO.getGpa() != 0.0) {
                    student.setGpa(studentDTO.getGpa());
                }
                if (studentDTO.getAge() != 0) {
                    student.setAge(studentDTO.getAge());
                }
                studentRepository.save(student);
            }
        }
        return studentDTO;
    }

    @Override
    public boolean deleteStudentById(int id) {
        Student student = new Student();
        boolean check = false;
        try {
            student = studentRepository.getStudentById(id);
        } catch (Exception e) {
        } finally {
            if (student != null) {
                studentRepository.delete(student);
                check = true;
            }else {
                check = false;
            }
        }
        return check;
    }
}
