package com.jobseek.dao;

import com.jobseek.entity.Recruiter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecruiterDao extends JpaRepository<Recruiter,Long> {
}
