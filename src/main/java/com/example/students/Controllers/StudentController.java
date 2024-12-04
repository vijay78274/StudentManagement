package com.example.students.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import jakarta.servlet.http.HttpServletRequest;
import com.example.students.Services.StudentService;


@Controller
@RequestMapping("/public")
public class StudentController {
    @Autowired
    StudentService service;

    @GetMapping("/getall")
    public String showStudents(Model model) {
        model.addAttribute("students",service.getAllStudents()); //List.of(student1, student2,student3)
        return "students"; // the name of your Thymeleaf template
    }
    @GetMapping("/sessionId")
    public ResponseEntity<String> adminUser(HttpServletRequest http){
        return ResponseEntity.ok("This session Id is: "+http.getSession().getId());
    }
    @GetMapping("/csrfToken")
    public ResponseEntity<CsrfToken> getMethodName(HttpServletRequest http) {
        return ResponseEntity.ok((CsrfToken)http.getAttribute("_csrf"));
    }
    
}
