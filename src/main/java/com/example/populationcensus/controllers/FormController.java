package com.example.populationcensus.controllers;

import com.example.populationcensus.dtos.ApiResponse;
import com.example.populationcensus.dtos.ValidationResponse;
import com.example.populationcensus.models.*;
import com.example.populationcensus.security.UserPrincipal;
import com.example.populationcensus.service.FormService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/forms")
@CrossOrigin(origins = "${app.cors.allowed-origins}")
public class FormController {
    private final FormService formService;

    public FormController(FormService formService) {
        this.formService = formService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Form>> createForm(
            @AuthenticationPrincipal UserPrincipal currentUser) {
        Form form = formService.createForm(currentUser);
        return ResponseEntity.ok(new ApiResponse<>(form, "Form created successfully", 200));
    }

    @GetMapping("/agent")
    public ResponseEntity<ApiResponse<List<Form>>> getFormsByAgent(
            @AuthenticationPrincipal UserPrincipal currentUser) {
        List<Form> forms = formService.getFormsByAgent(currentUser);
        return ResponseEntity.ok(new ApiResponse<>(forms, "Forms retrieved successfully", 200));
    }

    @GetMapping("/{formId}")
    public ResponseEntity<ApiResponse<Form>> getFormById(
            @PathVariable String formId,
            @AuthenticationPrincipal UserPrincipal currentUser) {
        Form form = formService.getFormById(formId, currentUser);
        return ResponseEntity.ok(new ApiResponse<>(form, "Form retrieved successfully", 200));
    }


    @PostMapping("/{formId}/step1")
    public ResponseEntity<ApiResponse<Form>> submitStep1(
            @PathVariable String formId,
            @Valid @RequestBody LocationInfo locationInfo,
            @AuthenticationPrincipal UserPrincipal currentUser) {
        Form form = formService.submitStep1(formId, locationInfo, currentUser);
        return ResponseEntity.ok(new ApiResponse<>(form, "Location information submitted successfully", 200));
    }


    @PostMapping("/{formId}/step2")
    public ResponseEntity<ApiResponse<Form>> submitStep2(
            @PathVariable String formId,
            @Valid @RequestBody HouseholdRoster householdRoster,
            @AuthenticationPrincipal UserPrincipal currentUser) {
        Form form = formService.submitStep2(formId, householdRoster, currentUser);
        return ResponseEntity.ok(new ApiResponse<>(form, "Household roster submitted successfully", 200));
    }


    @PostMapping("/{formId}/step3")
    public ResponseEntity<ApiResponse<Form>> submitStep3(
            @PathVariable String formId,
            @Valid @RequestBody HouseholdUnit householdUnit,
            @AuthenticationPrincipal UserPrincipal currentUser) {
        Form form = formService.submitStep3(formId, householdUnit, currentUser);
        return ResponseEntity.ok(new ApiResponse<>(form, "Household unit submitted successfully", 200));
    }

    @PostMapping("/{formId}/step4")
    public ResponseEntity<ApiResponse<Form>> submitStep4(
            @PathVariable String formId,
            @Valid @RequestBody TemporaryAbsentees temporaryAbsentees,
            @AuthenticationPrincipal UserPrincipal currentUser) {
        Form form = formService.submitStep4(formId, temporaryAbsentees, currentUser);
        return ResponseEntity.ok(new ApiResponse<>(form, "Temporary absentees submitted successfully", 200));
    }


    @PostMapping("/{formId}/step5")
    public ResponseEntity<ApiResponse<Form>> submitStep5(
            @PathVariable String formId,
            @Valid @RequestBody FertilityMember fertilityMember,
            @AuthenticationPrincipal UserPrincipal currentUser) {
        Form form = formService.submitStep5(formId, fertilityMember, currentUser);
        return ResponseEntity.ok(new ApiResponse<>(form, "Fertility information submitted successfully", 200));
    }


    @PostMapping("/{formId}/step6")
    public ResponseEntity<ApiResponse<Form>> submitStep6(
            @PathVariable String formId,
            @Valid @RequestBody EconomicActivityData economicActivityData,
            @AuthenticationPrincipal UserPrincipal currentUser) {
        Form form = formService.submitStep6(formId, economicActivityData, currentUser);
        return ResponseEntity.ok(new ApiResponse<>(form, "Economic activity submitted successfully", 200));
    }


    @PostMapping("/{formId}/step7")
    public ResponseEntity<ApiResponse<Form>> submitStep7(
            @PathVariable String formId,
            @Valid @RequestBody Disabilities disabilities,
            @AuthenticationPrincipal UserPrincipal currentUser) {
        Form form = formService.submitStep7(formId, disabilities, currentUser);
        return ResponseEntity.ok(new ApiResponse<>(form, "Disabilities information submitted successfully", 200));
    }


    @PostMapping("/{formId}/step8")
    public ResponseEntity<ApiResponse<Form>> submitStep8(
            @PathVariable String formId,
            @Valid @RequestBody AgriculturalActivity agriculturalActivity,
            @AuthenticationPrincipal UserPrincipal currentUser) {
        Form form = formService.submitStep8(formId, agriculturalActivity, currentUser);
        return ResponseEntity.ok(new ApiResponse<>(form, "Agricultural activity submitted successfully", 200));
    }


    @GetMapping("/{formId}/validate")
    public ResponseEntity<ApiResponse<ValidationResponse>> validateForm(
            @PathVariable String formId,
            @AuthenticationPrincipal UserPrincipal currentUser) {
        ValidationResponse validation = formService.validateForm(formId, currentUser);
        return ResponseEntity.ok(new ApiResponse<>(validation, "Form validation completed", 200));
    }

    @PostMapping("/{formId}/complete")
    public ResponseEntity<ApiResponse<Form>> completeForm(
            @PathVariable String formId,
            @AuthenticationPrincipal UserPrincipal currentUser) {
        Form form = formService.completeForm(formId, currentUser);
        return ResponseEntity.ok(new ApiResponse<>(form, "Form completed successfully", 200));
    }
}