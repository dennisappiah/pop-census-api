package com.example.populationcensus.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "disabilities")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Disabilities {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Pattern(regexp = "Yes|No", message = "Sight must be either 'Yes' or 'No'")
    @Column(name = "sight", nullable = false, columnDefinition = "VARCHAR(3) CHECK (sight IN ('Yes', 'No'))")
    private String sight;

    @Pattern(regexp = "Yes|No", message = "Hearing must be either 'Yes' or 'No'")
    @Column(name = "hearing", nullable = false, columnDefinition = "VARCHAR(3) CHECK (hearing IN ('Yes', 'No'))")
    private String hearing;

    @Pattern(regexp = "Yes|No", message = "Speech must be either 'Yes' or 'No'")
    @Column(name = "speech", nullable = false, columnDefinition = "VARCHAR(3) CHECK (speech IN ('Yes', 'No'))")
    private String speech;

    @Pattern(regexp = "Yes|No", message = "Physical must be either 'Yes' or 'No'")
    @Column(name = "physical", nullable = false, columnDefinition = "VARCHAR(3) CHECK (physical IN ('Yes', 'No'))")
    private String physical;

    @Pattern(regexp = "Yes|No", message = "Intellectual must be either 'Yes' or 'No'")
    @Column(name = "intellectual", nullable = false, columnDefinition = "VARCHAR(3) CHECK (intellectual IN ('Yes', 'No'))")
    private String intellectual;

    @Pattern(regexp = "Yes|No", message = "Emotional must be either 'Yes' or 'No'")
    @Column(name = "emotional", nullable = false, columnDefinition = "VARCHAR(3) CHECK (emotional IN ('Yes', 'No'))")
    private String emotional;
}
