package com.example.populationcensus.models.stepsFour;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fullName;

    @Embedded
    private DateOfBirth dateOfBirth;

    @Column(nullable = false)
    private String age;

    @Column(nullable = false)
    private String nationality;

    @Column(nullable = false)
    private String ethnicity;

}
