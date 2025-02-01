package com.example.populationcensus.models;


import com.example.populationcensus.models.stepseight.Crop;
import com.example.populationcensus.models.stepseight.Livestock;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "agricultural_activities")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AgriculturalActivity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "agricultural_activity_id")
    private List<Crop> crops;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "agricultural_activity_id")
    private List<Livestock> livestock;
}