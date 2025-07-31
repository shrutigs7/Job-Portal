package com.jobseek.dto;

import com.jobseek.entity.JobType;
import com.jobseek.entity.Recruiter;
import com.jobseek.entity.Skill;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class JobRespDto {

    private String title;
    private JobType type;
    private String description;
    private String location;
//    private Recruiter recruiter;
    private Set<Skill> jskills = new HashSet<Skill>();
}
