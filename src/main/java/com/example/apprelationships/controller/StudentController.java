package com.example.apprelationships.controller;

import com.example.apprelationships.entity.Address;
import com.example.apprelationships.entity.Student;
import com.example.apprelationships.entity.Subject;
import com.example.apprelationships.payload.StudentDto;
import com.example.apprelationships.repository.AddressRepository;
import com.example.apprelationships.repository.StudentRepository;
import com.example.apprelationships.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {

    @Autowired
    AddressRepository addressRepository;
    @Autowired
    SubjectRepository subjectRepository;
    @Autowired
    StudentRepository studentRepository;


    @RequestMapping(value = "/student", method = RequestMethod.POST)
    public String addStudent(@RequestBody StudentDto studentDto) {
        Address address = new Address(studentDto.getCity(), studentDto.getDistrict(), studentDto.getStreet());
        Address savedAddress = addressRepository.save(address);

//        List<Subject> subjectList = studentDto.getSubjectList();
//        List<Subject> saqlanmoqchi = new ArrayList<>();
//        List<Subject> saqlangan = new ArrayList<>();
//        for (Subject subject : subjectList) {
//            Subject newSubject = new Subject();
//            newSubject.setSubjectName(subject.getSubjectName());
//            saqlanmoqchi.add(newSubject);
//            saqlangan = subjectRepository.saveAll(saqlanmoqchi);
//        }


//        student.setSubjects(subjectRepository.saveAll(studentDto.getSubjectList()));

        Student student = new Student();
        student.setFirstName(studentDto.getFirstName());
        student.setLastName(studentDto.getLastName());
        student.setPhoneNumber(studentDto.getPhoneNumber());
        student.setAddress(savedAddress);
        student.setSubjects(studentDto.getSubjectList());
        studentRepository.save(student);
        return "Ishlayapti";


    }

    // VAZIRLIK UCHUN
    @RequestMapping(value = "/studentForMinistry", method = RequestMethod.GET)
    public Page<Student> students(@RequestParam int page) {

        Pageable pageable = PageRequest.of(page, 10);
        Page<Student> studentPage = studentRepository.findAll(pageable);
        return studentPage;
    }

    @RequestMapping(value ="/studentForRector/{id}",method = RequestMethod.GET)
    public Page<Student> studentForRector(@PathVariable Integer id,int page){

        Pageable pageable=PageRequest.of(page,10);
        Page<Student> studentPage = studentRepository.findAllByGroup_Faculty_UniversityId(id, pageable);
        return studentPage;
    }
}









