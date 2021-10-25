package com.example.apprelationships.repository;

import com.example.apprelationships.entity.Group;
import com.example.apprelationships.payload.GroupDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
public interface GroupRepository extends JpaRepository<Group, Integer> {
//    @Query(value = "select * from groups gr join faculty f on gr.faculty_id=f.id join university u on u.id=f.university_id ", nativeQuery = true)
//    List<Group> vazirlikUchun();

//    @Query(value = "select new com.example.apprelationships.payload.GroupDAO( gr.group_name,f.faculty_name, u.university_name,u.address_id) from groups gr\n" +
//            "join faculty f on gr.faculty_id=f.id\n" +
//            "join university u on u.id=f.university_id ", nativeQuery = true)
//    List<GroupDAO> vazirlikUchun(); Native query da GroupDAO ga cast qilib bo'lmadi

    @Query(value = "select new com.example.apprelationships.payload.GroupDAO(gr.groupName,f.facultyName,u.universityName,a.id) from" +
            " groups gr join Faculty f on f.id=gr.faculty.id " +
            "join University  u on u.id=f.university.id join Address a on a.id=u.address.id")
    List<GroupDAO> vazirlikUchun(); //GroupDAO ga cast qilib bo'lar ekan

    List<Group> findByFaculty_University_Id(Integer faculty_university_id);

    List<Group> findByFacultyId(Integer faculty_id);

}
