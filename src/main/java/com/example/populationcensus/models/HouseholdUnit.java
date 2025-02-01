package com.example.populationcensus.models;

import com.example.populationcensus.models.stepsFour.Person;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "household_unit")
public class HouseholdUnit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "household_id")
    private List<Person> people = new ArrayList<>();


}