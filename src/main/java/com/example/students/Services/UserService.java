package com.example.students.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.students.Models.Users;
import com.example.students.Repository.UserRepository;

@Service
public class UserService {
    @Autowired 
    private UserRepository userRepository;

    public Users save(Users user){
        return userRepository.save(user);
    }
}
