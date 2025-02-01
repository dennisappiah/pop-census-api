package com.example.populationcensus.models.stepsseven;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChildrenSurviving {
    @Column(name = "children_surviving_male")
    private Integer male;

    @Column(name = "children_surviving_female")
    private Integer female;
}
