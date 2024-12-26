package com.example.students;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import com.example.students.Models.Student;
import com.example.students.Models.Users;
import com.example.students.Repository.StudentRepository;
import com.example.students.Repository.UserRepository;
import com.example.students.Services.StudentService;
import com.example.students.Services.UserService;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest
@AutoConfigureMockMvc
class StudentsApplicationTests {
	@Autowired
	private StudentService service;
	@Autowired
    private MockMvc mockMvc;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserService service2;
	@Autowired
	private StudentRepository repository;
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
				.param("email","singh.gopal.@gmail.com")
				.param("rawPassword", "Gopal@123")
                .param("role", "ADMIN")
            	.contentType("application/x-www-form-urlencoded"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Gopal Singh"))
                .andExpect(jsonPath("$.role").value("ADMIN"));
	}
	// @Test
	// void updateUser() throws Exception{
	// 	// Users user = new Users("Vijay Singh","Vijay@123","ADMIN");
	// 	// service2.save(user);
	// 	mockMvc.perform(put("/admin/update")
	// 			.param("id",1L)
	// 			.param("name", "John jones")
	// 			.param("email","jones.john.@gmail.com")
    //             .param("branch","CSE")
	// 			.param("year",3)
	// 			.contentType("application/x-www-form-urlencoded"))
    //             .andExpect(status().isOk())
    //             .andExpect(jsonPath("$.name").value("Gopal Singh"))
    //             .andExpect(jsonPath("$.role").value("ADMIN"));
	// }
	@Test
void loadbyUser() {
    Users foundUser = userRepository.findByName("Gopal Singh");
    System.out.println("Found user: " + foundUser);
    assertThat(foundUser).isNotNull();

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    String hashPassword = passwordEncoder.encode("Gopal@123");
    System.out.println("Encoded password: " + hashPassword);
    System.out.println("User's stored password: " + foundUser.getPassword());

    boolean matches = passwordEncoder.matches("Gopal@123", foundUser.getPassword());
    System.out.println("Password matches: " + matches);
    assertThat(matches).isTrue();
}
// @Test
// void findEmail(){
// 	Users user = userRepository.findByEmail("drake.palmer.example@gmail.com");
// 	System.out.println(user);
// }

	@Test
	public void updateStudent(){
		Student student = repository.findById(4L)
                .orElseThrow(() -> new RuntimeException("Student not found"));
		System.out.println(student.getName()+" "+student.getBranch()+" "+student.getEmail());
		student.setName("Shavkat Rakhmanov");
		student.setEmail("rakhmanov.shavkat.@gmail.com");
		System.out.println(student.getName()+" "+student.getEmail());
		service2.updateStudent(4L, student);	
	}
}
