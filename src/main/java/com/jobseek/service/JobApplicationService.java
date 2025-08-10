package com.jobseek.service;

import com.jobseek.dto.ApiResponse;
import com.jobseek.dto.JobApplicationDto;

public interface JobApplicationService {

    ApiResponse addJobApplication(JobApplicationDto jobApplicationDto);

    ApiResponse updateApplicationStatus(JobApplicationDto jobApplicationDto);

    String getApplicationStatus(JobApplicationDto jobApplicationDto);

}
