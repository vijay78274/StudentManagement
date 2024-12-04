package com.example.students.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.students.Models.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long>{
    // public void findbyName(String name);
}
