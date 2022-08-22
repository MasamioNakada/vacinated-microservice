package com.example.demo.controller;

import com.example.demo.exceptions.Apiunauthorized;
import com.example.demo.model.Student;
import com.example.demo.service.AuthService;
import com.example.demo.service.StudentService;
import com.example.demo.validator.AuthValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private AuthService authService;

    @Autowired
    private AuthValidator validator;

    @PostMapping(path="oauth/client_credential/accesstoken", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> login(@RequestBody MultiValueMap<String, String> paramMap, @RequestParam("grant_type") String grantType) throws Apiunauthorized {

        validator.validate(paramMap,grantType);
        return ResponseEntity.ok(authService.login(paramMap.getFirst("client_id"), paramMap.getFirst("client_secret")));
    }

    @Autowired
    private StudentService studentService;

    @PostMapping
    public ResponseEntity<Student> saveStudent (@Valid @RequestBody Student student){
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.saveStudent(student)) ;
    }

    @GetMapping
    public ResponseEntity<Page<Student>> getAllStudent (
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size,
            @RequestParam(required = false, defaultValue = "false") Boolean enablePagination

    ){
        return ResponseEntity.ok(studentService.getAllStudent(page, size, enablePagination));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteStudent(@PathVariable ("id") Long id){
        studentService.deleteStudent(id);
        return ResponseEntity.ok(!studentService.existById(id));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Optional<Student>> findStudentById(@PathVariable("id")Long id){
        return ResponseEntity.status(HttpStatus.OK).body(studentService.findById(id)) ;
    }

    @PutMapping
    public ResponseEntity<Student> editStudent (@Valid @RequestBody Student student){
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.editStudent(student)) ;
    }
}
