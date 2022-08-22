package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.*;

@Data
@Entity
@Table(name = "Users")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "Name cannot be null")
    @Size(min = 1, max = 50, message = "Name must be less than 50")
    private String name;
    @NotBlank(message = "Lastname cannot be null")
    @Size(min = 1, max = 100, message = "Name must be less than 100")
    private String lastName;
    @NotBlank
    @Min(value = 1000000000, message = "Invalid Cedula")
    @Max(value = 999999999, message = "Invalid Cedula")
    private int cedula;
    @Email(message = "Email should be valid")
    private String email;
    @Size(min = 8, max = 16, message = "Name must be grater than 8")
    private String password;



}
