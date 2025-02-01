package com.example.populationcensus.models;
import com.example.populationcensus.models.stepsseven.ChildrenBornPast12Months;
import com.example.populationcensus.models.stepsseven.ChildrenEverBorn;
import com.example.populationcensus.models.stepsseven.ChildrenSurviving;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "fertility_members")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FertilityMember {
    @Id
    private String id;

    @Embedded
    private ChildrenEverBorn childrenEverBorn;

    @Embedded
    private ChildrenSurviving childrenSurviving;

    @Embedded
    private ChildrenBornPast12Months childrenBornPast12Months;
}