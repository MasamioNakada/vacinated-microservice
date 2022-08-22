package com.example.demo.service;

import com.example.demo.model.Student;
import com.example.demo.repository.IStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private IStudentRepository iStudentRepository;

    @PostMapping
    public Student saveStudent (Student student){
        if (student.getId() == null){
            return iStudentRepository.save(student);
        }
        return null;
    }
    
    @GetMapping
    public Page<Student> getAllStudent (Integer page, Integer size, Boolean eneablePagination){
        return iStudentRepository.findAll(eneablePagination ? PageRequest.of(page, size): Pageable.unpaged());
    }

    public Optional<Student> findById(long id){
        return iStudentRepository.findById(id);
    }

    @DeleteMapping(value = "{id}")
    public void deleteStudent(@PathVariable ("id") Long id){
        iStudentRepository.deleteById(id);
    }

    @PutMapping
    public Student editStudent (Student student) {
        if (student.getId() != null && iStudentRepository.existsById(student.getId())){
            return iStudentRepository.save(student);
        }
        return null;
    }

    public boolean existById(Long id) {
        return iStudentRepository.existsById(id);

    }
}
