package com.example.populationcensus.models.stepsfive;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
@Table(name = "economic_activity")
public class EconomicActivity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String activityId;  // This maps to the id in the interface

    @Column(nullable = false)
    private String establishmentName;

    @Column(nullable = false)
    private String mainProduct;

    @Column(nullable = false)
    private String industryCode;

    @Column(nullable = false)
    private String employmentStatus;

    @Column(nullable = false)
    private String employmentSector;


}