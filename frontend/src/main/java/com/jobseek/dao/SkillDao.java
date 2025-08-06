package com.jobseek.dao;

import com.jobseek.entity.Job;
import com.jobseek.entity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SkillDao extends JpaRepository<Skill,Long> {

//        List<Skill> findByNameContainingIgnoreCase(String keyword);
    }

