package com.jobseek.dto;

import com.jobseek.entity.Admin;
import com.jobseek.entity.Candidate;
import com.jobseek.entity.Recruiter;
import com.jobseek.entity.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDto {

    private long userId;
    private String password;
    private String email;
    private Role role;
//    private Candidate candidate;
//    private Admin admin;
//    private Recruiter recruiter;
}
