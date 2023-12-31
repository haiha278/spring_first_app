package com.example.spring_first_app.service;

import com.example.spring_first_app.common.Messages;
import com.example.spring_first_app.dto.StudentDTO;
import com.example.spring_first_app.entity.Student;
import com.example.spring_first_app.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@Repository
public class StudentServiceImp implements StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public ArrayList<StudentDTO> getAllStudents() {
        ArrayList<StudentDTO> studentDTOS = new ArrayList<>();
        List<Student> studentList = studentRepository.findAll();
        for (Student student : studentList) {
            StudentDTO studentDTO = new ModelMapper().map(student, StudentDTO.class);
            studentDTOS.add(studentDTO);
        }
        return studentDTOS;
    }

    @Override
    public StudentDTO getStudentById(int id) {
        try {
            Student student = studentRepository.getStudentById(id);
            if (student != null) {
                StudentDTO studentDTO = new ModelMapper().map(student, StudentDTO.class);
                return studentDTO;
            }
        } catch (Exception e) {
            log.error(Messages.HANDLED_EXCEPTION);
        }
        return null;
    }

    @Override
    public StudentDTO insertStudent(StudentDTO studentDTO) {
        Student student = new ModelMapper().map(studentDTO, Student.class);
        studentRepository.save(student);
        studentDTO.setId(student.getId());
        return studentDTO;
    }

    @Override
    public StudentDTO updateStudentById(int id, StudentDTO studentDTO) {
        Student student = new Student();
        try {
            student = studentRepository.getStudentById(id);
            if (student != null) {
                if (studentDTO.getName() != null) {
                    student.setName(studentDTO.getName());
                }
                if (studentDTO.getGpa() != 0.0) {
                    student.setGpa(studentDTO.getGpa());
                }
                if (studentDTO.getAge() != 0) {
                    student.setAge(studentDTO.getAge());
                }
                studentRepository.save(student);
                return studentDTO;
            }
        } catch (Exception e) {
            log.error(Messages.HANDLED_EXCEPTION);
        }
        return null;
    }

    @Override
    public boolean deleteStudentById(int id) {
        Student student = new Student();
        boolean check = false;
        try {
            student = studentRepository.getStudentById(id);
            if (student != null) {
                studentRepository.delete(student);
                check = true;
            } else {
                check = false;
            }
        } catch (Exception e) {
            log.error(Messages.HANDLED_EXCEPTION);
        }
        return check;
    }
}
