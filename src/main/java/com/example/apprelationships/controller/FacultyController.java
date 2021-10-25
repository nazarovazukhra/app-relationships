package com.example.apprelationships.controller;

import com.example.apprelationships.entity.Faculty;
import com.example.apprelationships.entity.University;
import com.example.apprelationships.payload.FacultyDto;
import com.example.apprelationships.repository.FacultyRepository;
import com.example.apprelationships.repository.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/faculty")
public class FacultyController {
    @Autowired
    FacultyRepository facultyRepository;
    @Autowired
    UniversityRepository universityRepository;

    @PostMapping
    public String add(@RequestBody FacultyDto facultyDto) {
        boolean exists = facultyRepository.existsByFacultyNameAndUniversityId(facultyDto.getFacultyName(), facultyDto.getUniversityId());
        if (exists)
            return "Such faculty already exists in this university";
        Optional<University> optionalUniversity = universityRepository.findById(facultyDto.getUniversityId());
        if (!optionalUniversity.isPresent())
            return "Such university not found";
        Faculty faculty = new Faculty(null, facultyDto.getFacultyName(), optionalUniversity.get());
        facultyRepository.save(faculty);

        return "Faculty added";
    }

    @PutMapping("/{id}")
    public String edit(@PathVariable Integer id, @RequestBody FacultyDto facultyDto) {

        Optional<Faculty> optionalFaculty = facultyRepository.findById(id);
        if (!optionalFaculty.isPresent())
            return "Such faculty not found";


        Optional<University> optionalUniversity = universityRepository.findById(facultyDto.getUniversityId());
        if (!optionalUniversity.isPresent())
            return "Such university not found";

        Faculty editingFaculty = optionalFaculty.get();
        editingFaculty.setFacultyName(facultyDto.getFacultyName());
        editingFaculty.setUniversity(optionalUniversity.get());
        facultyRepository.save(editingFaculty);
        return "Faculty edited";
    }

    // OLIY TA'LIM VAZIRLIGI UCHUN CHUNKI ULAR HAMMA UNIVERISTY NI KO'RA OLISHI KERAK
    @GetMapping
    private List<Faculty> getAll() {
        return facultyRepository.findAll();
    }

    @GetMapping("/byUniversityId/{universityId}")
    public List<Faculty> getByUniversityId(@PathVariable Integer universityId) {
        List<Faculty> result1 = facultyRepository.findFacultiesByUniversityId(universityId);
        List<Faculty> result2 = facultyRepository.getAllFacultiesByUniversityId(universityId);
        facultyRepository.getAllFacultiesByNative(universityId);
        return result2;
    }
}
