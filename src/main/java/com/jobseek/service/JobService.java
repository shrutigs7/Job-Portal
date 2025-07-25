package com.jobseek.service;

import com.jobseek.entity.Job;

import java.util.List;

public interface JobService {

    public List<Job> getAllJobs();

    public Long getTotalNumberOfJobs();
}
