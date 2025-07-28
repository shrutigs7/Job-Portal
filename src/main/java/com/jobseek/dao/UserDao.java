package com.jobseek.dao;

import com.jobseek.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserDao extends JpaRepository<User,Long> {
    List<User> findByRoleIn(List<String> roles);
}
