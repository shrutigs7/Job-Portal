package com.jobseek.dto;

import com.jobseek.entity.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpDto {
    private String password;
    private String email;
    private Role role;
}



