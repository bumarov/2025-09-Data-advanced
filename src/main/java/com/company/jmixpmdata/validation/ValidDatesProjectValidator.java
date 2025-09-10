package com.company.jmixpmdata.validation;

import com.company.jmixpmdata.entity.Project;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDateTime;

public class ValidDatesProjectValidator implements ConstraintValidator<ValidDatesProject, Project> {

    @Override
    public boolean isValid(Project value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }

        LocalDateTime startDate = value.getStartDate();
        LocalDateTime endDate = value.getEndDate();

        if (startDate == null || endDate == null) {
            return true;
        }

        return startDate.isBefore(endDate);
    }
}
