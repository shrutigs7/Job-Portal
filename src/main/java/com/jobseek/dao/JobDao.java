package com.jobseek.dao;

import com.jobseek.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JobDao extends JpaRepository<Job,Long> {

    List<Job> findAllByRecruiterUserId(Long userId);
    Optional<Job> findAllByJobIdAndIsActiveTrue(long jobId);

    List<Job> findAllByRecruiterUserIdAndIsActiveTrue(Long userId);
}
