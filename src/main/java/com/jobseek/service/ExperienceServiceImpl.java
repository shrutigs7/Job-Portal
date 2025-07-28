package com.jobseek.service;

import com.jobseek.dao.CandidateDao;
import com.jobseek.dto.ExperienceReqDto;
import com.jobseek.entity.Candidate;
import com.jobseek.entity.Experience;
import com.jobseek.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;


@Service
@Transactional
@AllArgsConstructor
public class ExperienceServiceImpl  implements ExperienceService{

    public final CandidateDao candidateDao;
    public final ModelMapper modelMapper;

    @Override
    public Experience addExperience(long userId, ExperienceReqDto experienceReqDto) {
        Experience experience = modelMapper.map(experienceReqDto,Experience.class);
        Candidate candidate = candidateDao.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("No user found"));
        if(candidate.getExperienceList() == null){
            candidate.setExperienceList(new ArrayList<>());
        }
        candidate.getExperienceList().add(experience);
        candidateDao.save(candidate);
        return experience;
    }
}
