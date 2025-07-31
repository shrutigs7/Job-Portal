package com.jobseek.dao;

import com.jobseek.entity.Candidate;
import com.jobseek.entity.Job;
import com.jobseek.entity.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobApplicationDao extends JpaRepository<JobApplication, Long> {

    boolean existsByCandidateAndJob(Candidate candidate, Job job);
}
