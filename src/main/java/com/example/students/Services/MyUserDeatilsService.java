package com.example.students.Services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.students.MyUserDetails;
import com.example.students.Models.Users;
import com.example.students.Repository.UserRepository;

@Service
public class MyUserDeatilsService implements UserDetailsService{
    @Autowired
    public UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("Attempting to load user: " + username);
        if(username.isEmpty()||username==null){
            System.out.println("username is empty");
        }
        // Users user = repository.findByName(username);
        Users user = repository.findByEmail(username);
        if(username.isEmpty()||username==null){
            throw new UsernameNotFoundException("user not found");
        }
        System.out.println("User found: " + user.getEmail());
        return new MyUserDetails(user);
    }

    public Users save(Users user){
        return repository.save(user);
    }
}
