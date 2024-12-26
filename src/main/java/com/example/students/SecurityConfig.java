package com.example.students;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.authentication.AuthenticationProvider;
// import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
// import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.http.SessionCreationPolicy;
// import org.springframework.security.core.userdetails.User;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.example.students.Services.MyUserDeatilsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    MyUserDeatilsService service;
    // @Bean 
    // public UserDetailsService service(){
    //     UserDetails user1 = User.withDefaultPasswordEncoder()
    //                         .username("public")
    //                         .password("pass")
    //                         .roles("PUBLIC")
    //                         .build();
    //     UserDetails user2 = User.withDefaultPasswordEncoder()
    //                         .username("admin")
    //                         .password("pass")
    //                         .roles("ADMIN")
    //                         .build();
    //     return new InMemoryUserDetailsManager(user1,user2);
    // }
    // @Bean
    // public AuthenticationManager authManager(AuthenticationConfiguration config) throws Exception {
    //     return config.getAuthenticationManager();
    // }
    // @Bean 
    // public AuthenticationProvider authProvder(){
    //     DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    //     provider.setUserDetailsService(service);
    //     provider.setPasswordEncoder(encoder());
    //     return provider;
    // }
    
    // @SuppressWarnings({ "deprecation", "removal" })
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http
        .csrf(csrf -> csrf.disable()) 
        .authorizeHttpRequests(request -> 
            request
                .requestMatchers("/login", "/css/**", "/js/**").permitAll() 
                // .requestMatchers("/admin/user").permitAll() 
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/public/**").hasAnyRole("USER","ADMIN")
                .anyRequest().authenticated() 
        )
        .formLogin(form -> 
            form
                .loginPage("/login") 
                .loginProcessingUrl("/perform_login") 
                .defaultSuccessUrl("/public/getall", true) 
                .failureUrl("/login?error=true") 
                .permitAll() 
        )
        .logout(logout -> 
            logout
                .logoutUrl("/logout") 
                .logoutSuccessUrl("/login?logout=true") 
                .permitAll()
        )
        .build();
    }

    @Bean 
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }
}
