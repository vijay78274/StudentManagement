package com.example.students.Controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.students.Models.Student;
import com.example.students.Models.Users;
import com.example.students.Repository.UserRepository;
import com.example.students.Services.MyUserDeatilsService;
import com.example.students.Services.StudentService;
import com.example.students.Services.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    MyUserDeatilsService userService;
    @Autowired
    UserService service2;
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
        String encryptedPassword = passwordEncoder.encode(name+782);
        Users user = new Users(name,email,encryptedPassword,"USER");
        userRepository.save(user);
        return "redirect:/admin/getall"; // Redirect to the home page
    }
   
    @PostMapping("/user")
    public ResponseEntity<Users> registerUser(@RequestParam String name,@RequestParam String email,@RequestParam String rawPassword,@RequestParam String role) {
        Users user = new Users();
        user.setName(name);

        // Encrypt the raw password
        String encryptedPassword = passwordEncoder.encode(rawPassword);
        user.setPassword(encryptedPassword);
        user.setRole(role);
        user.setEmail(email);
        // return userRepository.save(user);
        Users savedUser = userRepository.save(user);
        return ResponseEntity.ok(savedUser);
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        Student student = service2.getStudentById(id);
        if (student == null) {
            throw new RuntimeException("Student with ID " + id + " not found");
        }
        else
            System.out.println(student);
        model.addAttribute("student", student);
        return "update"; 
    }

    @PutMapping("/update/{id}")
    public String updateStudent(@PathVariable Long id, @ModelAttribute Student student) {
        service2.updateStudent(id, student);
        return "redirect:/admin/getall"; 
    }
    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        service2.deleteStudent(id);
        return "Employee with ID " + id + " deleted successfully.";
    }
    @DeleteMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {
        service2.deleteStudent(id); 
        return "redirect:/admin/getall"; 
    }
}
