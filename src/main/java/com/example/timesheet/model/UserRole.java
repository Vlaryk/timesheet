package com.example.timesheet.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "users_roles")
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id; //primaryKey

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "role_id") //сделать отдельный класс
    private Long roleId;
}
