package com.jobseek.dao;

import com.jobseek.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobDao extends JpaRepository<Job,Long> {

    List<Job> findAllByRecruiterUserId(Long userId);

}
