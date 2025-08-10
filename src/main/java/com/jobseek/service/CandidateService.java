package com.jobseek.service;

import com.jobseek.dto.*;
import com.jobseek.entity.Job;
import com.jobseek.entity.Skill;

import java.util.List;
import java.util.Set;

public interface CandidateService {

    CandidateRespDto createCandidate(CandidateReqDto candidateReqDto, long userId);

    CandidateRespDto addSkills(Long userId, SkillsReqDto cskill);

    CandidateProfileDto getCandidateProfile(Long userId);

    List<JobRespDto> getAppliedJobs(Long userId);

    List<CandidateRespDto> searchCandidatesBySkill(String skillName);

    List<CandidateRespDto> searchCandidatesByExperience(double minYears);

    CandidateRespDto updateCandidateProfile(long userId, CandidateReqDto dto);

    List<CandidateRespDto> getAllCandidates();
}
