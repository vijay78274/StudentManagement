package com.example.students;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.students.Models.Users;

public class MyUserDetails implements UserDetails{

    Users users;
    public MyUserDetails(Users users) {
        this.users=users;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // ArrayList<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();     
        // SimpleGrantedAuthority role1 = new SimpleGrantedAuthority("ROLE_USER");
        // SimpleGrantedAuthority role2 = new SimpleGrantedAuthority("ROLE_ADMIN");    
        // roles.add(role1);
        // roles.add(role2);
        // return roles;
        ArrayList<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
        roles.add(new SimpleGrantedAuthority("ROLE_" + users.getRole())); // Dynamically add the role
        return roles;
    }


    @Override
    public String getPassword() {
        // TODO Auto-generated method stub
        return users.getPassword();
    }

    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        return users.getName();
    }
    
}
