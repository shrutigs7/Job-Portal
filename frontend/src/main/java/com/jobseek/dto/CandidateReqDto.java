package com.jobseek.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
public class CandidateReqDto {
    @NotBlank(message = "Candidate name is required")
    private String name;
    @Past(message = "Date must be in the past")
    private LocalDate dateOfBirth;
    @NotBlank(message = "mobile number is required")
    private String mobileNo;
    @NotBlank(message = "linkedin link is required")
    private String linkedIn;
    @NotBlank(message = "github link is required")
    private String gitHub;

}
