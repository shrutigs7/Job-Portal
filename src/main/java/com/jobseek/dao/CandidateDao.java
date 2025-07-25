package com.jobseek.dao;

import com.jobseek.entity.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateDao extends JpaRepository<Candidate,Long> {
}
