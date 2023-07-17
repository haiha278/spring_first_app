package com.example.spring_first_app.entity;

import lombok.Data;
import javax.persistence.*;

@Entity
@Table(name = "Student_Class")
@Data
public class StudentGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;
    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;
}
