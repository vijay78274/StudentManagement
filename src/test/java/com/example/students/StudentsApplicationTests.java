package com.example.students;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import com.example.students.Models.Student;
import com.example.students.Models.Users;
import com.example.students.Repository.UserRepository;
import com.example.students.Services.StudentService;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

}
