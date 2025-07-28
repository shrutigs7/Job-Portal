package com.jobseek.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
public class CandidateRespDto {
    private String name;
    private LocalDate dateOfBirth;
    private String mobileNo;
    private String linkedIn;
    private String gitHub;
    private LocalDate lastUpdated;
    private Set<String> skills;
}
