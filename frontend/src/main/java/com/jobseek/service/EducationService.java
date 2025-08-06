package com.jobseek.service;

import com.jobseek.dto.ApiResponse;
import com.jobseek.dto.EducationReqDto;
import com.jobseek.entity.Education;

public interface EducationService {

    Education addEducation(EducationReqDto eductionReqDto, Long userId);

    ApiResponse updateEducation(EducationReqDto educationReqDto,Long userId,Long eduId);

    ApiResponse deleteEducation(Long userId,Long eduId);
}
