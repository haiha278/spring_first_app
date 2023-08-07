package com.example.spring_first_app.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Student")
@Data
public class Student {
    @Id
    @GeneratedValue
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "age")
    private int age;
    @Column(name = "gpa")
    private double gpa;
    @OneToMany(mappedBy = "student")
    private List<StudentGroup> studentGroups;

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", gpa=" + gpa +
                ", studentGroups=" + studentGroups +
                '}';
    }
}
