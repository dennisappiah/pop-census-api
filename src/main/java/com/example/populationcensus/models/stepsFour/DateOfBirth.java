package com.example.populationcensus.models.stepsFour;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class DateOfBirth {
    @Column(nullable = false)
    private String day;

    @Column(nullable = false)
    private String month;

    @Column(nullable = false)
    private String year;

}
