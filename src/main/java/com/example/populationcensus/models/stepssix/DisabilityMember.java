package com.example.populationcensus.models.stepssix;

import com.example.populationcensus.models.Disabilities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "disability-members")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DisabilityMember {
    @Id
    private String id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "disabilities_id")
    private Disabilities disabilities;

    private String otherDisabilitySpecify;
    private boolean ownsMobilePhone;
    private boolean usesInternet;
}