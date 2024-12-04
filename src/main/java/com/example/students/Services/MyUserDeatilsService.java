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
        Users user = repository.findByName(username);
        if(user==null){
            throw new UsernameNotFoundException("user not found");
        }
        return new MyUserDetails(user);
    }

    public Users save(Users user){
        return repository.save(user);
    }
}
