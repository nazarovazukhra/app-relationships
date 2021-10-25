package com.example.apprelationships.repository;

import com.example.apprelationships.entity.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface FacultyRepository extends JpaRepository<Faculty, Integer> {

    boolean existsByFacultyNameAndUniversityId(String facultyName, Integer universityId);
    //   Optional<Faculty> findByIdAndUniversityId(Integer id, Integer universityId); hozircha kerak emas

    //   1   2    3  holatlarda bir xil natija beradi, 1   2  holatni   ishlash yo'li  join orqali  xuddiki 3  holat kabi

    //1
    List<Faculty> findFacultiesByUniversityId(Integer university_id);


    //3
    @Query("select f from Faculty  f  where  f.university.id=:universityId")
    //    @Query("select f from Faculty  f  where  f.university.id=?1")
    List<Faculty> getAllFacultiesByUniversityId(Integer universityId);


    //3
    @Query(value = "select * from faculty f join university u on u.id = f.university_id where u.id = 3", nativeQuery = true)
    List<Faculty> getAllFacultiesByNative(Integer universityId);
}
