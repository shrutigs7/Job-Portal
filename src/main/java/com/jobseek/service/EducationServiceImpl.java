package com.jobseek.service;

import com.jobseek.dao.CandidateDao;
import com.jobseek.dto.ApiResponse;
import com.jobseek.dto.EducationReqDto;
import com.jobseek.entity.Candidate;
import com.jobseek.entity.Education;
import com.jobseek.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class EducationServiceImpl implements EducationService{

    public final ModelMapper modelMapper;

    public final CandidateDao candidateDao;

    @Override
    public Education addEducation(EducationReqDto eductionReqDto, Long userId) {
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

    @Override
    public ApiResponse updateEducation(EducationReqDto educationReqDto, Long userId, Long eduId) {
        Candidate candidate = candidateDao.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("user not found"));
        List<Education> educationList = candidate.getEducationList();
        boolean updated = false;
        for(Education edu : educationList){
            if(edu.getEduId() == eduId){
                edu.setDegree(educationReqDto.getDegree());
                edu.setGrade(educationReqDto.getGrade());
                edu.setStartYear(educationReqDto.getStartYear());
                edu.setEndYear(educationReqDto.getEndYear());
                edu.setUniversity(educationReqDto.getUniversity());
                updated = true;
                break;
            }
        }

        if (!updated) {
            throw new ResourceNotFoundException("Education entry not found for update");
        }
        candidateDao.save(candidate);
        return new ApiResponse("Education updated successfully");
    }

    @Override
    public ApiResponse deleteEducation(Long userId, Long eduId) {
        Candidate candidate = candidateDao.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Candidate not found"));

        boolean removed = candidate.getEducationList().removeIf(edu -> ((Long)edu.getEduId()).equals(eduId));

        if (!removed) {
            throw new ResourceNotFoundException("Education entry not found for deletion");
        }

        candidateDao.save(candidate); // Triggers deletion because orphanRemoval = true
        return new ApiResponse("Education deleted successfully");
    }


}
