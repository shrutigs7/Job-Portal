package com.jobseek.service;

import com.jobseek.dto.EducationReqDto;
import com.jobseek.entity.Education;

public interface EducationService {

    Education addEducation(EducationReqDto eductionReqDto, Long userId);
}
