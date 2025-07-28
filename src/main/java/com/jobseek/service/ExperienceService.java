package com.jobseek.service;

import com.jobseek.dto.ExperienceReqDto;
import com.jobseek.entity.Experience;

public interface ExperienceService {

    Experience addExperience(long userId, ExperienceReqDto experienceReqDto);
}
