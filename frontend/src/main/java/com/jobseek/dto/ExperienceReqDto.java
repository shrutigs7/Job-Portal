package com.jobseek.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ExperienceReqDto {

    @NotBlank(message = "Role is required")
    private String role;
    @NotBlank(message = "companyName is required")
    private String companyName;
    @Past
    private LocalDate startDate;
    private LocalDate endDate;
    private float expInYears;
    @NotBlank(message = "Description is required")
    private String description;
}
