package com.jobseek.dto;

import com.jobseek.entity.Skill;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class SkillsReqDto {

    private Set<Long> skillIds;
}
