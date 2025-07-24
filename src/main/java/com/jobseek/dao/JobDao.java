package com.jobseek.dao;

import com.jobseek.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobDao extends JpaRepository<Job,Long> {

}
