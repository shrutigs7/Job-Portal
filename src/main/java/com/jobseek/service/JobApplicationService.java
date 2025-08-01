package com.jobseek.service;

import com.jobseek.dto.ApiResponse;
import com.jobseek.dto.JobApplicationDto;
import com.jobseek.entity.Job;

import java.util.List;

public interface JobApplicationService {

    ApiResponse addJobApplication(JobApplicationDto jobApplicationDto);

//    List<Job> getAppliedJobs(Long userId);
}
