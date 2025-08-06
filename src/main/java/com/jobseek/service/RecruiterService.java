package com.jobseek.service;

import com.jobseek.dto.RecruiterProfileDto;
import com.jobseek.dto.RecruiterReqDto;
import com.jobseek.dto.RecruiterRespDto;

public interface RecruiterService {

    RecruiterRespDto addRecruiter(long userId, RecruiterReqDto recruiterReqDto);

    RecruiterProfileDto getRecruiterProfile(Long userId);
}
