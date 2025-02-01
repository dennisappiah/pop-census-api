package com.example.populationcensus.models;


import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    private String password;

    private String email;


    @Enumerated(EnumType.STRING)
    private UserRole role;

    public enum UserRole {
        AGENT, SUPERVISOR, ADMIN
    }
}