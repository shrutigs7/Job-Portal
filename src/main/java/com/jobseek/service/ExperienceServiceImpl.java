package com.jobseek.service;

import com.jobseek.dao.CandidateDao;
import com.jobseek.dto.ApiResponse;
import com.jobseek.dto.ExperienceReqDto;
import com.jobseek.entity.Candidate;
import com.jobseek.entity.Experience;
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

    @Override
    public ApiResponse updateExperience(ExperienceReqDto experienceReqDto, Long userId, Long expId) {
        Candidate candidate = candidateDao.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Candidate not found"));

        List<Experience> experienceList = candidate.getExperienceList();
        boolean updated = false;

        for (Experience exp : experienceList) {
            if (((Long)exp.getExpId()).equals(expId)) {
                exp.setRole(experienceReqDto.getRole());
                exp.setCompanyName(experienceReqDto.getCompanyName());
                exp.setStartDate(experienceReqDto.getStartDate());
                exp.setEndDate(experienceReqDto.getEndDate());
                exp.setExpInYears(experienceReqDto.getExpInYears());
                exp.setDescription(experienceReqDto.getDescription());
                updated = true;
                break;
            }
        }

        if (!updated) {
            throw new ResourceNotFoundException("Experience entry not found for update");
        }

        candidateDao.save(candidate); // Save updated list
        return new ApiResponse("Experience updated successfully");
    }


    @Override
    public ApiResponse deleteExperience(Long userId, Long expId) {
        Candidate candidate = candidateDao.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Candidate not found"));

        boolean removed = candidate.getExperienceList()
                .removeIf(exp -> ((Long)exp.getExpId()).equals(expId));

        if (!removed) {
            throw new ResourceNotFoundException("Experience entry not found for deletion");
        }

        candidateDao.save(candidate); // orphanRemoval = true should delete it
        return new ApiResponse("Experience deleted successfully");
    }

}
