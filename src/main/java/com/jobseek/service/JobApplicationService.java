package com.jobseek.service;

import com.jobseek.dto.ApiResponse;
import com.jobseek.dto.JobApplicationDto;
import com.jobseek.entity.ApplicationStatus;

public interface JobApplicationService {

    ApiResponse addJobApplication(JobApplicationDto jobApplicationDto);

    ApiResponse updateApplicationStatus(JobApplicationDto jobApplicationDto);

    String getApplicationStatus(JobApplicationDto jobApplicationDto);

}
