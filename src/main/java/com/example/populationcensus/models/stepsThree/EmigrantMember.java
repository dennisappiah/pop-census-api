package com.example.populationcensus.models.stepsThree;

import com.example.populationcensus.models.TemporaryAbsentees;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "emigrant_members")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmigrantMember {
    @Id
    private String lineNo;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    private String relationshipToHead;

    @Column(nullable = false)
    private String code;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Sex sex;

    @Column(nullable = false)
    private String age;

    @Column(nullable = false)
    private String countryName;

    @Column(nullable = false)
    private String countryCode;

    @Column(nullable = false)
    private String yearOfDeparture;

    @Column(nullable = false)
    private String activityCode;

    private String otherActivity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "temporary_absentees_id")
    private TemporaryAbsentees temporaryAbsentees;
}