package com.jobseek.dto;

import com.jobseek.entity.JobType;
import com.jobseek.entity.Recruiter;
import com.jobseek.entity.Skill;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class JobRespDto {

    private long jobId;
    private String title;
    private String companyName;
    private int yearOfExperience;
    private JobType type;
    private String description;
    private String location;
    private LocalDate postedDate;
//    private Recruiter recruiter;
    private Set<Skill> jskills = new HashSet<Skill>();
}
