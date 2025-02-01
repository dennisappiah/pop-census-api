package com.example.populationcensus.models;


import com.example.populationcensus.models.stepsTwo.HouseholdMember;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "household_rosters")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HouseholdRoster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "householdRoster", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HouseholdMember> members = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_info_id")
    private LocationInfo locationInfo;


    public void addMember(HouseholdMember member) {
        members.add(member);
        member.setHouseholdRoster(this);
    }

    public void removeMember(HouseholdMember member) {
        members.remove(member);
        member.setHouseholdRoster(null);
    }
}
