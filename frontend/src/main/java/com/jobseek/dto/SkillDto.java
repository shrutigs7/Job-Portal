package com.jobseek.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SkillDto {
    private Long skillId;
    private String skillName;

    public SkillDto(Long skillId, String skillName) {
        this.skillId = skillId;
        this.skillName = skillName;
    }

    public SkillDto() {

    }
}
