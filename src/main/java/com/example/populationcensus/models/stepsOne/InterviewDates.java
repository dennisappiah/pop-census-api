package com.example.populationcensus.models.stepsOne;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.time.LocalDateTime;

@Embeddable
@Data
public class InterviewDates {
    private LocalDateTime dateStarted;
    private LocalDateTime dateCompleted;
}
