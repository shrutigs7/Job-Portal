package com.jobseek.dto;

import com.jobseek.entity.JobType;
import com.jobseek.entity.Recruiter;
import com.jobseek.entity.Skill;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.jobseek.entity.Job;

@Getter
@Setter
@NoArgsConstructor
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
    private Set<SkillDto> jskills = new HashSet<SkillDto>();


    public JobRespDto(Job job) {
        this.jobId = job.getJobId();
        this.title = job.getTitle();
        this.companyName = job.getCompanyName();
        this.yearOfExperience = job.getYearOfExperience();
        this.type = job.getType();
        this.description = job.getDescription();
        this.location = job.getLocation();
        this.postedDate = job.getPostedDate();

        // Map skills
//        this.jskills = job.getJskills().stream()
//                .map(skill -> new SkillDto(skill.getSkillId(), skill.getSkillName()))
//                .collect(Collectors.toSet());
    }

}
