package com.jobseek.service;

import com.jobseek.dao.CompanyDao;
import com.jobseek.dto.CompanyReqDto;
import com.jobseek.entity.Company;
import com.jobseek.exception.ApiException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class CompayServiceImpl implements CompanyService{

    public final ModelMapper modelMapper;
    public final CompanyDao companyDao;

    @Override
    public void addCompany(CompanyReqDto comapnyDTO) {
        try {
            Company newCompany = modelMapper.map(comapnyDTO, Company.class);
            companyDao.save(newCompany);
        } catch (RuntimeException e) {
            throw new ApiException(e.getMessage());
        }
    }
}
