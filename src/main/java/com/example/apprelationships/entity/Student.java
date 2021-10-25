package com.example.apprelationships.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"firstName", "lastName", "group_id"})})
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String firstName;


    @Column(nullable = false)
    private String lastName;

    private String phoneNumber;

    @OneToOne(optional = false)    //(optional=false) address siz bo'lishi kerak emas degani
    private Address address;

    @ManyToOne(optional = false)
    private Group group;

    @ManyToMany
    private List<Subject> subjects;

}
