package com.jobseek.service;

import com.jobseek.dto.CandidateReqDto;
import com.jobseek.dto.CandidateRespDto;

import java.util.List;

public interface CandidateService {

    CandidateRespDto createCandidate(CandidateReqDto candidateReqDto, long userId);

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
