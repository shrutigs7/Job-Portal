package com.jobseek.service;

import com.jobseek.dao.CompanyDao;
import com.jobseek.dao.RecruiterDao;
import com.jobseek.dao.UserDao;
import com.jobseek.dto.CompanyReqDto;
import com.jobseek.dto.RecruiterProfileDto;
import com.jobseek.dto.RecruiterReqDto;
import com.jobseek.dto.RecruiterRespDto;
import com.jobseek.entity.Company;
import com.jobseek.entity.Recruiter;
import com.jobseek.entity.User;
import com.jobseek.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class RecruiterServiceImpl implements RecruiterService{

    public final RecruiterDao recruiterDao;
    public final ModelMapper modelMapper;
    public final UserDao userDao;
    public final CompanyService companyService;

    @Override
    public RecruiterRespDto addRecruiter(long userId, RecruiterReqDto recruiterReqDto) {
        //set recruiter details
        Recruiter recruiter = modelMapper.map(recruiterReqDto, Recruiter.class);

        //find user form userId
        User user = userDao.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        //set recruiter onetoone in user
        user.setActive(true);
        recruiter.setUser(user);

        //add company details in company
        CompanyReqDto companyReqDto = modelMapper.map(recruiterReqDto,CompanyReqDto.class);
        Company company = companyService.addCompany(companyReqDto);
        recruiter.setCompany(company);
        return modelMapper.map(recruiterDao.save(recruiter), RecruiterRespDto.class);
    }

    @Override
    public RecruiterProfileDto getRecruiterProfile(Long userId) {
        Recruiter recruiter = recruiterDao.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("Recruiter not found"));
        recruiter.getCompany();
        return modelMapper.map(recruiter,RecruiterProfileDto.class);
    }
}
