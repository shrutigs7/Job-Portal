package com.jobseek.service;

import com.jobseek.dao.CandidateDao;
import com.jobseek.dto.EductionReqDto;
import com.jobseek.entity.Candidate;
import com.jobseek.entity.Education;
import com.jobseek.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
@Transactional
@AllArgsConstructor
public class EducationServiceImpl implements EducationService{

    public final ModelMapper modelMapper;

    public final CandidateDao candidateDao;

    @Override
    public Education addEducation(EductionReqDto eductionReqDto, Long userId) {
        Education education = modelMapper.map(eductionReqDto,Education.class);
        Candidate candidate = candidateDao.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("user not found"));
        if(candidate.getEducationList() == null){
            candidate.setEducationList(new ArrayList<>());
        }
        candidate.getEducationList().add(education);
        candidateDao.save(candidate);
        return education;
    }
}
