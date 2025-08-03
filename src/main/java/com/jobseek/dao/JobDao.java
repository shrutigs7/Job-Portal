package com.jobseek.dao;

import com.jobseek.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface JobDao extends JpaRepository<Job,Long> {

    List<Job> findAllByRecruiterUserId(Long userId);
    Optional<Job> findAllByJobIdAndIsActiveTrue(long jobId);

    List<Job> findAllByRecruiterUserIdAndIsActiveTrue(Long userId);

    @Query("SELECT j FROM Job j JOIN j.jskills s WHERE LOWER(s.skillName) LIKE LOWER(CONCAT('%', :skillName, '%'))")
    List<Job> findJobsBySkillName(@Param("skillName") String skillName);

    List<Job> findByYearOfExperienceLessThanEqual(int yearOfExperience);



}
