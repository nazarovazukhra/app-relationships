package com.example.apprelationships.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "groups")
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"groupName","faculty_id"})})
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String groupName;

    @ManyToOne(optional = false)
    private Faculty faculty;

//    @OneToMany             bundan ko'ra Student da @ManyToOne qilgan afzal
//    public List<Student> students;

}
