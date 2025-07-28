package com.jobseek.dao;

import com.jobseek.entity.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CandidateDao extends JpaRepository<Candidate, Long> {
//    Optional<Candidate> findByMobileNo(String mobileNo);
//
//    Optional<Candidate> findByLinkedIn(String linkedIn);
//
//    Optional<Candidate> findByGitHub(String gitHub);
//
//    List<Candidate> findByNameContainingIgnoreCase(String name);
}
