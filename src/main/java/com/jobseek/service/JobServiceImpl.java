package com.jobseek.service;

import com.jobseek.dao.JobDao;
import com.jobseek.entity.Job;
import com.jobseek.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class JobServiceImpl implements JobService{

    private final JobDao jobDao;

    @Override
    public List<Job> getAllJobs() {
        List<Job> jobs = jobDao.findAll();
        if(!jobs.isEmpty())
            return jobs;
        else
            throw new ResourceNotFoundException("No jobs yet registered");
    }
}
