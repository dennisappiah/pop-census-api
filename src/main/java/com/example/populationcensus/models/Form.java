package com.example.populationcensus.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;


@Data
@Entity
@Table(name = "forms")
@NoArgsConstructor
@AllArgsConstructor
public class Form {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private LocalDateTime dateCreated;
    private LocalDateTime dateCompleted;

    @Enumerated(EnumType.STRING)
    private FormStatus status;

    @Column(name = "agent_id")
    private Long agentId;

    private Integer currentStep;

    // Step 1: Location Information
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "location_info_id")
    private LocationInfo locationInfo;

    // Step 2: Household Roster
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "household_roster_id")
    private HouseholdRoster householdRoster;

    // Step 3: Household Unit
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "household_unit_id")
    private HouseholdUnit householdUnit;

    // Step 4: Temporary Absentees
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "temporary_absentees_id")
    private TemporaryAbsentees temporaryAbsentees;

    // Step 5: Fertility Information
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "fertility_member_id")
    private FertilityMember fertilityMember;

    // Step 6: Economic Activity
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "economic_activity_data_id")
    private EconomicActivityData economicActivityData;

    // Step 7: Disabilities
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "disabilities_id")
    private Disabilities disabilities;

    // Step 8: Agricultural Activity
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "agricultural_activity_id")
    private AgriculturalActivity agriculturalActivity;

    public enum FormStatus {
        IN_PROGRESS,
        COMPLETED
    }


    public Form(Long agentId) {
        this.agentId = agentId;
        this.dateCreated = LocalDateTime.now();
        this.status = FormStatus.IN_PROGRESS;
        this.currentStep = 1;
    }


    public void completeForm() {
        this.status = FormStatus.COMPLETED;
        this.dateCompleted = LocalDateTime.now();
    }

    public void moveToNextStep() {
        if (this.currentStep < 8) {
            this.currentStep++;
        } else {
            this.status = FormStatus.COMPLETED; // Mark as completed after step 8
        }
    }

    public void moveToPreviousStep() {
        if (this.currentStep > 1) {
            this.currentStep--;
        }
    }

    public boolean isComplete() {
        return this.status == FormStatus.COMPLETED;
    }

    @PrePersist
    protected void onCreate() {
        if (dateCreated == null) {
            dateCreated = LocalDateTime.now();
        }
        if (status == null) {
            status = FormStatus.IN_PROGRESS;
        }
        if (currentStep == null) {
            currentStep = 1;
        }
    }
}


