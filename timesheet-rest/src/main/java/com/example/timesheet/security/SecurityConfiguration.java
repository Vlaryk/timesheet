package com.example.timesheet.security;

import com.example.timesheet.model.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;

//@Configuration
//public class SecurityConfiguration {
//    @Bean
//    SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception{
//        return http
//                .authorizeHttpRequests(requests -> requests
//                        .requestMatchers("//project/**").hasAuthority("admin")
//                        .requestMatchers("//timesheets/**").hasAnyAuthority("admin,user")
//                        .anyRequest().authenticated()
//                )
//                .formLogin(Customizer.withDefaults())
//                .build();
//    }
//
//    @Bean
//    PasswordEncoder passwordEncoder () {
//        return new BCryptPasswordEncoder();
//    }
//}
