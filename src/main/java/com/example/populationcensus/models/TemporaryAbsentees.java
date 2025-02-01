package com.example.populationcensus.models;

import com.example.populationcensus.models.stepsThree.AbsentMember;
import com.example.populationcensus.models.stepsThree.EmigrantMember;
import com.example.populationcensus.models.stepsThree.YesNo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "temporary_absentees")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TemporaryAbsentees {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String absentCount;

    @OneToMany(mappedBy = "temporaryAbsentees", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AbsentMember> absentMembers = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private YesNo hasEmigrants;
//    public enum YesNo {
//        Yes, No
//    }
//

    @OneToMany(mappedBy = "temporaryAbsentees", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EmigrantMember> emigrants = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "household_id")
    private HouseholdRoster household;

    public void addAbsentMember(AbsentMember member) {
        absentMembers.add(member);
        member.setTemporaryAbsentees(this);
    }

    public void addEmigrant(EmigrantMember emigrant) {
        emigrants.add(emigrant);
        emigrant.setTemporaryAbsentees(this);
    }
}

