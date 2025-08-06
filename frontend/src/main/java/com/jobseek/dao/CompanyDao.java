package com.jobseek.dao;

import com.jobseek.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyDao extends JpaRepository<Company,Long> {
}
