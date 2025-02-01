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
public class ChildrenBornPast12Months {
    @Column(name = "children_born_12months_boy")
    private Integer boy;

    @Column(name = "children_born_12months_girl")
    private Integer girl;
}