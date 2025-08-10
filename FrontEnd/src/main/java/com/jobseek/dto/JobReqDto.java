package com.jobseek.dto;

import com.jobseek.entity.JobType;
import com.jobseek.entity.Recruiter;
import com.jobseek.entity.Skill;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class JobReqDto {

    private String title;
    private String companyName;
    private int yearOfExperience;
    private JobType type;
    private String description;
    private String location;
    private Recruiter recruiter;
    private Set<Long> jskills = new HashSet<Long>();

}
