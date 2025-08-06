package com.jobseek.service;

import com.jobseek.entity.Job;
import com.jobseek.entity.Skill;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SkillService {

    List<Skill> getAllSkills();
//    List<Skill> searchSkillsByName(String keyword);
}
