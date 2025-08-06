package com.jobseek.dto;

import com.jobseek.entity.Education;
import com.jobseek.entity.Experience;
import com.jobseek.entity.Skill;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class CandidateProfileDto extends UserDetailsDto{
    private String name;
    private LocalDate dateOfBirth;
    private String mobileNo;
    private String linkedIn;
    private String gitHub;
    private List<Education> educationList = new ArrayList<>();
    private List<Experience> experienceList = new ArrayList<>();
    private Set<Skill> cskills = new HashSet<>();
}
