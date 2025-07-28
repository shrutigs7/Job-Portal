package com.jobseek.dao;

import com.jobseek.entity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillDao extends JpaRepository<Skill,Long> {
}
