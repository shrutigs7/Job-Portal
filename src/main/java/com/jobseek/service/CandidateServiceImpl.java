package com.jobseek.service;

import com.jobseek.dao.CandidateDao;
import com.jobseek.dao.UserDao;
import com.jobseek.dto.CandidateReqDto;
import com.jobseek.dto.CandidateRespDto;
import com.jobseek.entity.Candidate;
import com.jobseek.entity.User;
import com.jobseek.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class CandidateServiceImpl implements CandidateService{

    public final CandidateDao candidateDao;
    public final UserDao userDao;
    public final ModelMapper modelMapper;

    @Override
    public CandidateRespDto createCandidate(CandidateReqDto candidateReqDto, long userId) {
        Candidate candidate = modelMapper.map(candidateReqDto,Candidate.class);
        User user = userDao.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("No user with passed userId"));
        candidate.setUser(user);
        return modelMapper.map(candidateDao.save(candidate), CandidateRespDto.class);
    }
}
