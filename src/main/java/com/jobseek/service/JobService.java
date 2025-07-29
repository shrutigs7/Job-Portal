package com.jobseek.service;

import com.jobseek.dto.ApiResponse;
import com.jobseek.dto.JobReqDto;
import com.jobseek.dto.JobRespDto;
import com.jobseek.entity.Job;

import java.util.List;

public interface JobService {

    List<JobRespDto> getAllJobs();

    List<JobRespDto> getAllJobs(Long userId);

    Job addJob(Long userId, JobReqDto jobReqDto);

    JobRespDto updateJob(Long jobId, JobReqDto jobReqDto);

    ApiResponse deleteJob(Long jobId);
}
