package com.example.students.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.students.Models.Student;
import com.example.students.Models.Users;
import com.example.students.Repository.StudentRepository;
import com.example.students.Repository.UserRepository;

@Service
public class UserService {
    @Autowired 
    private UserRepository userRepository;
    @Autowired
    private StudentRepository studentRepository;

    public Users save(Users user){
        return userRepository.save(user);
    }
    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElse(null); 
    }
    public void updateStudent(Long id, Student student) {
        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        existingStudent.setName(student.getName());
        existingStudent.setBranch(student.getBranch());
        existingStudent.setYear(student.getYear());
        existingStudent.setEmail(student.getEmail());
        studentRepository.save(existingStudent);
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
}
