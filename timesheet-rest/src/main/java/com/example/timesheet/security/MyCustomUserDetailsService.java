package com.example.timesheet.security;

import com.example.timesheet.model.Role;
import com.example.timesheet.model.User;
import com.example.timesheet.model.UserRole;
import com.example.timesheet.repository.RoleRepository;
import com.example.timesheet.repository.UserRepository;
import com.example.timesheet.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//@Component
//@RequiredArgsConstructor
//public class MyCustomUserDetailsService implements UserDetailsService {
//
//    //{SpringSecurity}
//    //... @UserDetailsService userDetailsService
//    //User[admin] --login--> [UserDetailsService userDetailsService.loadUserByUserName(login)]
//    //[UserDetails -> SecurityContext]
//
//    //в нашем случае юзеры хранятся в БД в таблице UserRepository
//    //строго говоря в этой реализации UserDetailsService можно загружать данные о пользователе из любого источника:
//    //внешний auth-service, ldap-server, ...
//    private final UserRepository userRepository;
//    private final UserRoleRepository userRoleRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepository.findByLogin(username).
//                orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
//
//
//        List<SimpleGrantedAuthority> userRoles = userRoleRepository.findByUserId(user.getId()).stream().map(it -> new SimpleGrantedAuthority(it.getRoleName().toString())).toList();
//        return new org.springframework.security.core.userdetails.User(
//                user.getLogin(),
//                user.getPassword(),
//                userRoles
//        );
//    }
//}
