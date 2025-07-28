package com.jobseek.service;

import com.jobseek.dto.EductionReqDto;
import com.jobseek.entity.Education;

public interface EducationService {

    Education addEducation(EductionReqDto eductionReqDto, Long userId);
}
