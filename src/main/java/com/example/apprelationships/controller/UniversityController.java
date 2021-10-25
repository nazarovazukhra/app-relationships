package com.example.apprelationships.controller;

import com.example.apprelationships.payload.UniversityDto;
import com.example.apprelationships.entity.Address;
import com.example.apprelationships.entity.University;
import com.example.apprelationships.repository.AddressRepository;
import com.example.apprelationships.repository.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UniversityController {
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    UniversityRepository universityRepository;

    @RequestMapping(value = "/university", method = RequestMethod.POST)
    public String addUniversity(@RequestBody UniversityDto universityDto) {
        Address address = new Address();
        address.setCity(universityDto.getCity());
        address.setDistrict(universityDto.getDistrict());
        address.setStreet(universityDto.getStreet());
        Address savedAddress = addressRepository.save(address);

        University university = new University();
        university.setUniversityName(universityDto.getUniversityName());
        university.setAddress(savedAddress);
        universityRepository.save(university);
        return "University added";

    }

    @RequestMapping(value = "/university/{id}", method = RequestMethod.PUT)
    public String editUniversity(@PathVariable Integer id, @RequestBody UniversityDto universityDto) {
        Optional<University> optionalUniversity = universityRepository.findById(id);
        if (optionalUniversity.isPresent()) {

            University editingUniversity = optionalUniversity.get();
            editingUniversity.setUniversityName(universityDto.getUniversityName());

            Address address = editingUniversity.getAddress();
            address.setCity(universityDto.getCity());
            address.setDistrict(universityDto.getDistrict());
            address.setStreet(universityDto.getStreet());

            addressRepository.save(address);
            universityRepository.save(editingUniversity);

            return "University edited";

        }
        return "Such university not found";
    }

    @RequestMapping(value = "/university/{id}", method = RequestMethod.DELETE)
    public String deleteUniversity(@PathVariable Integer id) {
//        universityRepository.deleteById(id);
//        return "University deleted";

        Optional<University> optionalUniversity = universityRepository.findById(id);
        if (!optionalUniversity.isPresent())
            return "Such university not found";
        University university = optionalUniversity.get();
        universityRepository.delete(university);

        // Avval university ni o'chirishimiz kerak keyin esa uning address ini o'chiramiz


        Address address = university.getAddress();
        addressRepository.delete(address);
        return "University deleted";

    }

    @RequestMapping(value = "/university", method = RequestMethod.GET)
    public List<University> getUniversities() {
        return universityRepository.findAll();
    }

}
