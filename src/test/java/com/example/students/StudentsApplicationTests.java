package com.example.students;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.example.students.Controllers.AdminController;
import com.example.students.Models.Student;
import com.example.students.Models.Users;
import com.example.students.Services.MyUserDeatilsService;
import com.example.students.Services.StudentService;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
class StudentsApplicationTests {
	@Autowired
	private StudentService service;
	@Autowired 
	private MyUserDeatilsService service2;

	@Autowired
    private MockMvc mockMvc;

	@Test
	void contextLoads() {
	}
	@Test
	void savStudent(){
		Student student = new Student();
		student.setName("Drake Palmer");
		student.setEmail("drake.palmer.example@gmail.com");
		student.setBranch("AI/ML");
		student.setYear(1);
		service.save(student); 
	}
	@Test
	void saveuser() throws Exception{
		// Users user = new Users("Vijay Singh","Vijay@123","ADMIN");
		// service2.save(user);
		mockMvc.perform(post("/admin/user")
				.param("name", "Gopal Singh")
				.param("rawPassword", "Gopal@123")
                .param("role", "ADMIN")
            	.contentType("application/x-www-form-urlencoded"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Gopal Singh"))
                .andExpect(jsonPath("$.role").value("ADMIN"));
	}
}
