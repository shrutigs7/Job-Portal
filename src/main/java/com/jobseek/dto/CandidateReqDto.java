package com.jobseek.dto;

import java.time.LocalDate;
import java.util.Set;

public class CandidateReqDto {
    private String name;
    private LocalDate dateOfBirth;
    private String mobileNo;
    private String linkedIn;
    private String gitHub;
    private Set<Long> skillIds;


}
