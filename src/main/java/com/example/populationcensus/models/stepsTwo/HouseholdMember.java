package com.example.populationcensus.models.stepsTwo;


import com.example.populationcensus.models.HouseholdRoster;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "household_members")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HouseholdMember {
    @Id
    private String personId;

    private String fullName;
    private String relationshipToHead;
    private String relationshipCode;

    @Enumerated(EnumType.STRING)
    private Sex sex;

//    public enum Sex {
//    M, F
//}

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "household_roster_id")
    private HouseholdRoster householdRoster;
}
