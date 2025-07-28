package com.jobseek.service;

import com.jobseek.dao.UserDao;
import com.jobseek.entity.User;
import com.jobseek.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class UserServiceImpl implements UserService{

    public final UserDao userDao;

    @Override
    public List<User> getAllUsers() {

        List<String> allowedRoles = Arrays.asList("CANDIDATE", "RECRUITER");
        return Optional.of(userDao.findByRoleIn(allowedRoles))
                .filter(user -> !user.isEmpty())
                .orElseThrow(() -> new ResourceNotFoundException("No users registered"));
    }
}
