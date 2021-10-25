package com.example.apprelationships.payload;

import com.example.apprelationships.entity.Subject;
import lombok.Data;

import java.util.List;

@Data
public class StudentDto {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String city;
    private String district;
    private String street;
    private List<Subject> subjectList;
}
