package com.jobseek.service;

import com.jobseek.dao.RecruiterDao;
import com.jobseek.dao.UserDao;
import com.jobseek.dto.SignInDto;
import com.jobseek.dto.SignUpDto;
import com.jobseek.dto.UserDetailsDto;
import com.jobseek.dto.UserResponseDto;
import com.jobseek.entity.Company;
import com.jobseek.entity.Recruiter;
import com.jobseek.entity.User;
import com.jobseek.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
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
    public final RecruiterDao recruiterDao;

    @Override
    public List<User> getAllUsers() {

        List<String> allowedRoles = Arrays.asList("CANDIDATE", "RECRUITER");
        return Optional.of(userDao.findByRoleIn(allowedRoles))
                .filter(user -> !user.isEmpty())
                .orElseThrow(() -> new ResourceNotFoundException("No users registered"));
    }

    @Override
    public UserResponseDto addUser(SignUpDto signUpDto) {
        User user = modelMapper.map(signUpDto,User.class);
        return modelMapper.map(userDao.save(user),UserResponseDto.class);
    }

    @Override
    public UserResponseDto getUser(SignInDto signInDto) {
        return Optional.of(userDao.findByEmail(signInDto.getEmail()))
                .map(user -> modelMapper.map(user,UserResponseDto.class))
                .orElseThrow(() -> new ResourceNotFoundException("User Not found"));

    }

    @Override
    public UserDetailsDto getUser(Long userId) {
        UserDetailsDto userDetailsDto =  userDao.findById(userId)
                .map(user -> modelMapper.map(user,UserDetailsDto.class))
                .orElseThrow(() -> new ResourceNotFoundException("User Not found"));

        switch (userDetailsDto.getRole()){
            case RECRUITER : Recruiter recruiter = recruiterDao.findById(userId)
                    .orElseThrow(() -> new ResourceNotFoundException("Recruiter Not found"));
                    userDetailsDto.setName(recruiter.getName());
                    userDetailsDto.setMobNumber(recruiter.getMobNumber());
                    userDetailsDto.setTitle(recruiter.getTitle());
                    Company company = recruiter.getCompany();
                    userDetailsDto.setCompanyName(company.getCompanyName());
                    userDetailsDto.setLocation(company.getLocation());
                    userDetailsDto.setIndustry(company.getIndustry());
                    userDetailsDto.setSize(company.getSize());
                    userDetailsDto.setFoundedYear(company.getFoundedYear());
                    userDetailsDto.setWebsite(company.getWebsite());
                    userDetailsDto.setDescription(company.getDescription());

                break;
        }
        return userDetailsDto;
    }
}
