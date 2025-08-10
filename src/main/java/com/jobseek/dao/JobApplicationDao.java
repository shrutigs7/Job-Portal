package com.jobseek.dao;

import com.jobseek.entity.Candidate;
import com.jobseek.entity.Job;
import com.jobseek.entity.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JobApplicationDao extends JpaRepository<JobApplication, Long> {

    boolean existsByCandidateAndJob(Candidate candidate, Job job);

    //    Optional<JobApplication> findByJobIdAndUserId(Long jobId, Long userId);
    @Query(value = "SELECT status FROM job_applications WHERE job_id = ?1 and candidate_id = ?2", nativeQuery = true)
    Optional<String> findStatusByCandidateIdAndJobId(Long jobId, Long userId);


    Optional<JobApplication> findByJob_JobIdAndCandidate_UserId(long jobId, long userId);
}
