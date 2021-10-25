package com.example.apprelationships.controller;

import com.example.apprelationships.entity.Subject;
import com.example.apprelationships.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/subject")
public class SubjectController {
    @Autowired
    SubjectRepository subjectRepository;

    @PostMapping
    public String add(@RequestBody Subject subject) {
        boolean existsBySubjectName = subjectRepository.existsBySubjectName(subject.getSubjectName());
        if (existsBySubjectName)
            return "Such subject already exists";
        subjectRepository.save(subject);
        return "Subject added";
    }
}
