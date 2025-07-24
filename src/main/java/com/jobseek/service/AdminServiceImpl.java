package com.jobseek.service;

import com.jobseek.dao.AdminDao;
import com.jobseek.entity.Admin;
import com.jobseek.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class AdminServiceImpl implements AdminService{

    private final AdminDao adminDao;

    @Override
    public Admin getAdmin(long id) {
       return adminDao.findById(id).orElseThrow(() -> new ResourceNotFoundException("Admin not found"));
    }
}
