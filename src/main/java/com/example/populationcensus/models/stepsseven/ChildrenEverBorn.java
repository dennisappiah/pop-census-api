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
public class ChildrenEverBorn {
    @Column(name = "children_ever_born_male")
    private Integer male;

    @Column(name = "children_ever_born_female")
    private Integer female;
}
