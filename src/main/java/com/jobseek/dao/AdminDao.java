package com.jobseek.dao;

import com.jobseek.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminDao extends JpaRepository<Admin,Long> {

}
