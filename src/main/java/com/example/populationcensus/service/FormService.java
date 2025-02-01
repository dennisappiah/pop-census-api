package com.example.populationcensus.service;

import com.example.populationcensus.dtos.ValidationResponse;
import com.example.populationcensus.exceptions.ResourceNotFoundException;
import com.example.populationcensus.exceptions.UnauthorizedException;
import com.example.populationcensus.exceptions.ValidateException;
import com.example.populationcensus.models.*;
import com.example.populationcensus.repository.FormRepository;
import com.example.populationcensus.repository.UserRepository;
import com.example.populationcensus.security.UserPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class FormService {
    private final FormRepository formRepository;
    private final UserRepository userRepository;

    public FormService(FormRepository formRepository, UserRepository userRepository) {
        this.formRepository = formRepository;
        this.userRepository = userRepository;
    }

    public Form createForm(UserPrincipal currentUser) {
        User agent = userRepository.findById(currentUser.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (agent.getRole() != User.UserRole.AGENT && agent.getRole() != User.UserRole.ADMIN) {
            throw new UnauthorizedException("Only agents and admins can create forms");
        }

        Form form = new Form(agent.getId());
        return formRepository.save(form);
    }

    public List<Form> getFormsByAgent(UserPrincipal currentUser) {
        verifyUserExists(currentUser);
        return formRepository.findByAgentIdOrderByDateCreatedDesc(currentUser.getId());
    }

    private void verifyUserExists(UserPrincipal currentUser) {
        userRepository.findById(currentUser.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    private void verifyFormAccess(String formId, UserPrincipal currentUser) {
        Form form = formRepository.findById(formId)
                .orElseThrow(() -> new ResourceNotFoundException("Form not found"));

        User user = userRepository.findById(currentUser.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (!form.getAgentId().equals(user.getId()) &&
                user.getRole() != User.UserRole.SUPERVISOR &&
                user.getRole() != User.UserRole.ADMIN) {
            throw new UnauthorizedException("You don't have access to this form");
        }
    }

    public Form getFormById(String formId, UserPrincipal currentUser) {
        verifyFormAccess(formId, currentUser);
        return formRepository.findById(formId)
                .orElseThrow(() -> new ResourceNotFoundException("Form not found"));
    }


    public Form submitStep1(String formId, LocationInfo locationInfo, UserPrincipal currentUser) {
        Form form = getFormById(formId, currentUser);
        validateCurrentStep(form, 1);
        validateLocationInfo(locationInfo);
        form.setLocationInfo(locationInfo);
        form.moveToNextStep();
        return formRepository.save(form);
    }


    public Form submitStep2(String formId, HouseholdRoster householdRoster, UserPrincipal currentUser) {
        Form form = getFormById(formId, currentUser);
        validateCurrentStep(form, 2);
        validateHouseholdRoster(householdRoster);
        form.setHouseholdRoster(householdRoster);
        form.moveToNextStep();
        return formRepository.save(form);
    }


    public Form submitStep3(String formId, HouseholdUnit householdUnit, UserPrincipal currentUser) {
        Form form = getFormById(formId, currentUser);
        validateCurrentStep(form, 3);
        validateHouseholdUnit(householdUnit);
        form.setHouseholdUnit(householdUnit);
        form.moveToNextStep();
        return formRepository.save(form);
    }


    public Form submitStep4(String formId, TemporaryAbsentees temporaryAbsentees, UserPrincipal currentUser) {
        Form form = getFormById(formId, currentUser);
        validateCurrentStep(form, 4);
        validateTemporaryAbsentees(temporaryAbsentees);
        form.setTemporaryAbsentees(temporaryAbsentees);
        form.moveToNextStep();
        return formRepository.save(form);
    }


    public Form submitStep5(String formId, FertilityMember fertilityMember, UserPrincipal currentUser) {
        Form form = getFormById(formId, currentUser);
        validateCurrentStep(form, 5);
        validateFertilityMember(fertilityMember);
        form.setFertilityMember(fertilityMember);
        form.moveToNextStep();
        return formRepository.save(form);
    }


    public Form submitStep6(String formId, EconomicActivityData economicActivityData, UserPrincipal currentUser) {
        Form form = getFormById(formId, currentUser);
        validateCurrentStep(form, 6);
        validateEconomicActivity(economicActivityData);
        form.setEconomicActivityData(economicActivityData);
        form.moveToNextStep();
        return formRepository.save(form);
    }


    public Form submitStep7(String formId, Disabilities disabilities, UserPrincipal currentUser) {
        Form form = getFormById(formId, currentUser);
        validateCurrentStep(form, 7);
        validateDisabilities(disabilities);
        form.setDisabilities(disabilities);
        form.moveToNextStep();
        return formRepository.save(form);
    }


    public Form submitStep8(String formId, AgriculturalActivity agriculturalActivity, UserPrincipal currentUser) {
        Form form = getFormById(formId, currentUser);
        validateCurrentStep(form, 8);
        validateAgriculturalActivity(agriculturalActivity);
        form.setAgriculturalActivity(agriculturalActivity);
        form.moveToNextStep(); // Move to the next step (which should mark the form as completed)
        form.setStatus(Form.FormStatus.COMPLETED); // Explicitly mark the form as completed
        return formRepository.save(form);
    }


    private void validateCurrentStep(Form form, int expectedStep) {
        if (form.getCurrentStep() != expectedStep) {
            throw new ValidateException("Invalid step sequence",
                    List.of("Please complete the previous steps first"));
        }
        if (form.getStatus() == Form.FormStatus.COMPLETED) {
            throw new ValidateException("Form already completed",
                    List.of("Cannot modify a completed form"));
        }
    }


    private void validateLocationInfo(LocationInfo locationInfo) {
        List<String> errors = new ArrayList<>();
        if (locationInfo.getRegionName() == null || locationInfo.getRegionName().trim().isEmpty()) {
            errors.add("Region name is required");
        }
        if (locationInfo.getDistrictName() == null || locationInfo.getDistrictName().trim().isEmpty()) {
            errors.add("District name is required");
        }
        if (!errors.isEmpty()) {
            throw new ValidateException("Location information validation failed", errors);
        }
    }

    private void validateHouseholdRoster(HouseholdRoster householdRoster) {
        List<String> errors = new ArrayList<>();
        if (householdRoster.getMembers() == null || householdRoster.getMembers().isEmpty()) {
            errors.add("At least one household member is required");
        }
        if (!errors.isEmpty()) {
            throw new ValidateException("Household roster validation failed", errors);
        }
    }

    private void validateHouseholdUnit(HouseholdUnit householdUnit) {
        List<String> errors = new ArrayList<>();
        if (householdUnit.getPeople() == null || householdUnit.getPeople().isEmpty()) {
            errors.add("At least one person is required in the household unit");
        }
        if (!errors.isEmpty()) {
            throw new ValidateException("Household unit validation failed", errors);
        }
    }

    private void validateTemporaryAbsentees(TemporaryAbsentees temporaryAbsentees) {
        List<String> errors = new ArrayList<>();
        if (temporaryAbsentees.getAbsentCount() == null) {
            errors.add("Absent count is required");
        }
        if (!errors.isEmpty()) {
            throw new ValidateException("Temporary absentees validation failed", errors);
        }
    }

    private void validateFertilityMember(FertilityMember fertilityMember) {
        List<String> errors = new ArrayList<>();
        if (fertilityMember.getId() == null) {
            errors.add("Fertility member ID is required");
        }
        if (!errors.isEmpty()) {
            throw new ValidateException("Fertility member validation failed", errors);
        }
    }

    private void validateEconomicActivity(EconomicActivityData economicActivityData) {
        List<String> errors = new ArrayList<>();
        if (economicActivityData.getActivities() == null || economicActivityData.getActivities().isEmpty()) {
            errors.add("At least one economic activity is required");
        }
        if (!errors.isEmpty()) {
            throw new ValidateException("Economic activity validation failed", errors);
        }
    }

    private void validateDisabilities(Disabilities disabilities) {
        // Add specific validation rules for disabilities if needed
    }

    private void validateAgriculturalActivity(AgriculturalActivity agriculturalActivity) {
        List<String> errors = new ArrayList<>();
    }

    public ValidationResponse validateForm(String formId, UserPrincipal currentUser) {
        Form form = getFormById(formId, currentUser);
        List<String> errors = new ArrayList<>();

        // Validate all required components
        if (form.getLocationInfo() == null) {
            errors.add("Location information is required");
        }
        if (form.getHouseholdRoster() == null) {
            errors.add("Household roster is required");
        }
        if (form.getHouseholdUnit() == null) {
            errors.add("Household unit is required");
        }
        if (form.getTemporaryAbsentees() == null) {
            errors.add("Temporary absentees information is required");
        }
        if (form.getFertilityMember() == null) {
            errors.add("Fertility member information is required");
        }
        if (form.getEconomicActivityData() == null) {
            errors.add("Economic activity data is required");
        }
        if (form.getDisabilities() == null) {
            errors.add("Disabilities information is required");
        }
        if (form.getAgriculturalActivity() == null) {
            errors.add("Agricultural activity information is required");
        }

        return new ValidationResponse(errors.isEmpty(), errors);
    }

    public Form completeForm(String formId, UserPrincipal currentUser) {
        Form form = getFormById(formId, currentUser);
        ValidationResponse validation = validateForm(formId, currentUser);

        if (!validation.isValid()) {
            throw new ValidateException("Form validation failed", validation.getErrors());
        }

        form.completeForm();
        return formRepository.save(form);
    }
}


