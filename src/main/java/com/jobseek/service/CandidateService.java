package com.jobseek.service;

import com.jobseek.dto.CandidateProfileDto;
import com.jobseek.dto.CandidateReqDto;
import com.jobseek.dto.CandidateRespDto;
import com.jobseek.dto.SkillsReqDto;
import com.jobseek.entity.Skill;

import java.util.List;
import java.util.Set;

public interface CandidateService {

    CandidateRespDto createCandidate(CandidateReqDto candidateReqDto, long userId);

    CandidateRespDto addSkills(Long userId, SkillsReqDto cskill);

    CandidateProfileDto getCandidateProfile(Long userId);

    List<CandidateRespDto> searchCandidatesBySkill(String skillName);

    List<CandidateRespDto> searchCandidatesByExperience(double minYears);

    CandidateRespDto updateCandidateProfile(long userId, CandidateReqDto dto);



//    CandidateRespDto updateCandidate(long userId, CandidateReqDto dto);
//
//    CandidateRespDto getCandidateById(long userId);
//
//    List<CandidateRespDto> getAllCandidates();
//
//    void deleteCandidate(long userId);
//
//    CandidateRespDto getCandidateByMobileNo(String mobileNo);
//
//    CandidateRespDto getCandidateByLinkedIn(String linkedIn);
//
//    CandidateRespDto getCandidateByGitHub(String gitHub);
//
//    List<CandidateRespDto> searchCandidatesByName(String name);
}
