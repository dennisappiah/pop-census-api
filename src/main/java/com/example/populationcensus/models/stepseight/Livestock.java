package com.example.populationcensus.models.stepseight;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "livestock")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Livestock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String code;

    private Integer quantity;
}