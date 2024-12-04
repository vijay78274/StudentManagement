package com.example.students.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.students.Models.Users;
@Repository
public interface UserRepository extends JpaRepository<Users,Long>{
    Users findByName(String name);
}
