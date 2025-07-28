package com.jobseek.dto;

import java.time.LocalDate;
import java.util.Set;

public class CandidateRespDto {
    private long userId;
    private String name;
    private LocalDate dateOfBirth;
    private String mobileNo;
    private String linkedIn;
    private String gitHub;
    private LocalDate lastUpdated;
    private Set<String> skills;
}
