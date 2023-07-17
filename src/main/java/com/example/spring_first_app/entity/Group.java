package com.example.spring_first_app.entity;

import lombok.Data;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Class")
@Data
public class Group {
    @Id
    @GeneratedValue
    private int id;
    @Column(name = "Class_Name")
    private String className;
    @OneToMany(mappedBy = "group")
    private List<StudentGroup> studentGroups;
}
