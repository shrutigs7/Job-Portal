package com.jobseek.dto;

import com.jobseek.entity.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDetailsDto {

    private long userId;
    private String password;
    private String email;
    private Role role;
    //recruiter
    private String name;
    private String mobNumber;
    private String title;
    //company
    private String companyName;
    private String location;
    private Industry industry;
    private Size size;
    private Integer foundedYear;
    private String website;
    private String description;
}
