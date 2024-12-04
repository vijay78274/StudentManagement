package com.example.students.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.students.Models.Student;
import com.example.students.Repository.StudentRepository;

@Service
public class StudentService {
    @Autowired
    StudentRepository repository;
    public List<Student> getAllStudents(){
        return repository.findAll();
    }
    public Student save(Student student){
        return repository.save(student);
    }
}
