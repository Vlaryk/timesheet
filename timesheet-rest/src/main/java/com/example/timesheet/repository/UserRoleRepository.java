package com.example.timesheet.repository;

import com.example.timesheet.model.Role;
import com.example.timesheet.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserRoleRepository extends JpaRepository<UserRole,Long> {

//    @Query(nativeQuery = true, value = "select role_name from users_roles ur where ur.user_id = :userId")
//    List<UserRole> findByUserId(Long id);
//    List<Role> findRoleByUserId(Long id);
//    List<Role> findByRoleId(Long id);
}
