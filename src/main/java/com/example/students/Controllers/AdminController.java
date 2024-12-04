package com.example.students.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.students.Models.Student;
import com.example.students.Models.Users;
import com.example.students.Repository.UserRepository;
import com.example.students.Services.MyUserDeatilsService;
import com.example.students.Services.StudentService;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    MyUserDeatilsService userService;
    @Autowired
    StudentService service;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    
    @GetMapping("/getall")
    public String showStudents(Model model) {
        model.addAttribute("students",service.getAllStudents()); //List.of(student1, student2,student3)
        return "admins"; // the name of your Thymeleaf template
    }

    @PostMapping("/add")
    public String addUser(@RequestParam String name, @RequestParam String email, @RequestParam String branch, @RequestParam int year) {
        Student student = new Student();
        student.setName(name);
        student.setEmail(email);
        student.setBranch(branch);
        student.setYear(year);
        service.save(student);
        // String password = name+782;
        String encryptedPassword = passwordEncoder.encode(name+782);
        Users user = new Users(name,encryptedPassword,"USER");
        userRepository.save(user);
        return "redirect:/admin/getall"; // Redirect to the home page
    }
   
    @PostMapping("/user")
    public ResponseEntity<Users> registerUser(@RequestParam String name,@RequestParam String rawPassword,@RequestParam String role) {
        Users user = new Users();
        user.setName(name);

        // Encrypt the raw password
        String encryptedPassword = passwordEncoder.encode(rawPassword);
        user.setPassword(encryptedPassword);
        user.setRole(role);

        // return userRepository.save(user);
        Users savedUser = userRepository.save(user);
        return ResponseEntity.ok(savedUser);
    }
}
