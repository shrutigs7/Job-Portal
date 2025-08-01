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
