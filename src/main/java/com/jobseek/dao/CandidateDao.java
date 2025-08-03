package com.jobseek.dao;

import com.jobseek.entity.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CandidateDao extends JpaRepository<Candidate, Long> {

    @Query("SELECT c FROM Candidate c JOIN c.cskills s WHERE LOWER(s.skillName) = LOWER(:skillName)")
    List<Candidate> findCandidatesBySkillName(String skillName);


}
