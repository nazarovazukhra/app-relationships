package com.example.apprelationships.controller;

import com.example.apprelationships.entity.Faculty;
import com.example.apprelationships.entity.Group;
import com.example.apprelationships.payload.GroupDAO;
import com.example.apprelationships.payload.GroupDto;
import com.example.apprelationships.repository.FacultyRepository;
import com.example.apprelationships.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/group")
public class GroupController {

    @Autowired
    GroupRepository groupRepository;
    @Autowired
    FacultyRepository facultyRepository;

    @PostMapping
    public String add(@RequestBody GroupDto groupDto) {
        Optional<Faculty> optionalFaculty = facultyRepository.findById(groupDto.getFacultyId());
        if (!optionalFaculty.isPresent())
            return "Such faculty not found";

        Group group = new Group(null, groupDto.getGroupName(), optionalFaculty.get());
        groupRepository.save(group);
        return "Group added";
    }


    @GetMapping("/vazirlikUchun")
    public List<GroupDAO> getAllGroups() {
//        return groupRepository.findAll();
        return groupRepository.vazirlikUchun();
    }

    @GetMapping("/rektorUchun/{univerId}")
    public List<Group> getGroupsForRector(@PathVariable Integer univerId) {
        return groupRepository.findByFaculty_University_Id(univerId);
    }

    @GetMapping("/dekanUchun/{facultyId}")
    public List<Group> getGroupsForDeccan(@PathVariable Integer facultyId) {
        return groupRepository.findByFacultyId(facultyId);
    }

    @GetMapping("/tutorUchun/{id}")
    public Group getGroupForTutor(@PathVariable Integer id) {
        Optional<Group> optionalGroup = groupRepository.findById(id);
        return optionalGroup.orElse(null);
    }

}
