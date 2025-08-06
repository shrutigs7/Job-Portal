package com.jobseek.service;

import com.jobseek.dao.CandidateDao;
import com.jobseek.dao.RecruiterDao;
import com.jobseek.dao.UserDao;
import com.jobseek.dto.*;
import com.jobseek.entity.Company;
import com.jobseek.entity.Recruiter;
import com.jobseek.entity.User;
import com.jobseek.exception.DuplicateResourceException;
import com.jobseek.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.jobseek.entity.Role.RECRUITER;

@Service
@Transactional
@AllArgsConstructor
public class UserServiceImpl implements UserService{

    public final UserDao userDao;
    public final ModelMapper modelMapper;
    public final CandidateService candidateService;
    public final RecruiterService recruiterService;
    public final BCryptPasswordEncoder passwordEncoder;
    @Override
    public List<User> getAllUsers() {

        List<String> allowedRoles = Arrays.asList("CANDIDATE", "RECRUITER");
        return Optional.of(userDao.findByRoleIn(allowedRoles))
                .filter(user -> !user.isEmpty())
                .orElseThrow(() -> new ResourceNotFoundException("No users registered"));
    }

    @Override
    public UserDetailsDto addUser(SignUpDto signUpDto) {
        User user1 = userDao.findByEmail(signUpDto.getEmail())
            .orElseThrow(() -> new ResourceNotFoundException("No users registered"));
        if(user1 != null)
            throw new DuplicateResourceException("user already exists");
        User user = new User();
        user.setEmail(signUpDto.getEmail());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword())); // ✅ Encrypt!
        user.setRole(signUpDto.getRole());
        return modelMapper.map(userDao.save(user),UserDetailsDto.class);
    }

    @Override
    public UserResponseDto getUser(SignInDto signInDto) {
        return Optional.of(userDao.findByEmail(signInDto.getEmail()))
                .map(user -> modelMapper.map(user,UserResponseDto.class))
                .orElseThrow(() -> new ResourceNotFoundException("User Not found"));

    }

    @Override
    public UserDetailsDto getUser(Long userId) {
        User user =  userDao.findById(userId)
//                .map(user -> modelMapper.map(user,UserDetailsDto.class))
                .orElseThrow(() -> new ResourceNotFoundException("User Not found"));

        switch (user.getRole()) {
            case CANDIDATE -> {
                CandidateProfileDto candidateProfile = candidateService.getCandidateProfile(user.getUserId());
                candidateProfile.setEmail(user.getEmail());
                candidateProfile.setRole(user.getRole());
                return candidateProfile;
            }

            case RECRUITER -> {
                RecruiterProfileDto recruiterProfile = recruiterService.getRecruiterProfile(user.getUserId());
                recruiterProfile.setEmail(user.getEmail());
                recruiterProfile.setRole(user.getRole());
                return recruiterProfile;
            }
            default -> throw new IllegalArgumentException("Unsupported role: " + user.getRole());
        }
    }

}
