package com.example.populationcensus.models;

import com.example.populationcensus.models.stepsfive.EconomicActivity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@Table(name = "economic_activity_data")
public class EconomicActivityData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "economic_activity_data_id")
    private List<EconomicActivity> activities = new ArrayList<>();

}