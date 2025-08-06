package com.jobseek.dto;


import com.jobseek.entity.Company;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecruiterProfileDto extends UserDetailsDto {

    private String name;
    private String mobNumber;
    private String title;
    private Company company;
//    private String companyName;
//    private String location;
//    private Industry industry;
//    private Size size;
//    private Integer foundedYear;
//    private String website;
//    private String description;
}
