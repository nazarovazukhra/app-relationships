package com.example.apprelationships.repository;

import com.example.apprelationships.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {

    boolean existsBySubjectName(String subjectName);
}
