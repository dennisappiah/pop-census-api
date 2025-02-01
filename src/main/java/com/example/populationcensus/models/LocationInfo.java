package com.example.populationcensus.models;

import com.example.populationcensus.models.stepsOne.InterviewDates;
import com.example.populationcensus.models.stepsOne.ResidenceType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "location_info")
@NoArgsConstructor
@AllArgsConstructor
public class LocationInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String regionName;
    private String districtName;
    private String localityName;
    private String address;
    private String phone1;
    private String phone2;

    private String enumAreaCode;

    private String nhisNumber;
    private String eaType;
    private String localityCode;
    private String structureNumber;
    private String householdNumber;

    @Enumerated(EnumType.STRING)
    private ResidenceType residenceType;


    @Embedded
    private InterviewDates interviewDates;
}

