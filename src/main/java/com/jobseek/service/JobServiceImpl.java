package com.jobseek.service;

import com.jobseek.dao.JobDao;
import com.jobseek.entity.Job;
import com.jobseek.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class JobServiceImpl implements JobService{

    private final JobDao jobDao;

    @Override
    public List<Job> getAllJobs() {
        return Optional.of(jobDao.findAll())
                .filter(jobs -> !jobs.isEmpty())
                .orElseThrow(() -> new ResourceNotFoundException("No jobs yet registered"));
    }

    @Override
    public Long getTotalNumberOfJobs() {
        return Optional.of(jobDao.count())
                .filter(count -> count != 0L)
                .orElseThrow(() -> new ResourceNotFoundException("No jobs listed"));
    }
}
