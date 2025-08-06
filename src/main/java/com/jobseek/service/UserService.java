package com.jobseek.service;

import com.jobseek.dto.SignInDto;
import com.jobseek.dto.SignUpDto;
import com.jobseek.dto.UserDetailsDto;
import com.jobseek.dto.UserResponseDto;
import com.jobseek.entity.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    UserResponseDto getUser(SignInDto signInDto);

    UserDetailsDto getUser(Long userId);

    UserDetailsDto addUser(SignUpDto signUpDto);
}
