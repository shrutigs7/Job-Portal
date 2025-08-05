package com.jobseek.dao;

import com.jobseek.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserDao extends JpaRepository<User,Long> {
    List<User> findByRoleIn(List<String> roles);

    Optional<User> findByEmail(String email);
}
