package com.example.spring_first_app.repository;

import com.example.spring_first_app.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    @Query(value = "select s from Student s where s.id = :id")
    Student getStudentById(@Param("id") Integer integer);


}
