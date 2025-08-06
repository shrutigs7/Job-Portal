package com.jobseek.service;

import com.jobseek.dto.ApiResponse;
import com.jobseek.dto.ExperienceReqDto;
import com.jobseek.entity.Experience;

public interface ExperienceService {

    Experience addExperience(long userId, ExperienceReqDto experienceReqDto);

    ApiResponse updateExperience(ExperienceReqDto experienceReqDto, Long userId, Long expId);

    ApiResponse deleteExperience(Long userId, Long expId);
}
