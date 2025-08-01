package com.jobseek.service;

import com.jobseek.dao.SkillDao;
import com.jobseek.entity.Job;
import com.jobseek.entity.Skill;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class SkillServiceImpl implements SkillService{

    private final SkillDao skillDao;


//    @Override
//    public List<Skill> searchSkillsByName(String keyword) {
//        return skillDao.findByNameContainingIgnoreCase(keyword);
//    }
}
