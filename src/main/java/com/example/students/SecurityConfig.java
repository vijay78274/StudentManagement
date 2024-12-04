package com.example.students;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
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

    @Bean 
    public AuthenticationProvider authProvder(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(service);
        provider.setPasswordEncoder(encoder());
        return provider;
    }
    
    // @SuppressWarnings({ "deprecation", "removal" })
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http.csrf(customizer->customizer.disable())
        .authorizeHttpRequests(request->request.anyRequest().authenticated()).
        // .authorizeRequests(request->request.anyRequest().permitAll()).
        formLogin(Customizer.withDefaults()).
        httpBasic(Customizer.withDefaults()).
        // sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).
        build();
    }

    @Bean 
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }
}
