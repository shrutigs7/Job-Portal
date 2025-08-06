package com.jobseek.dto;

import com.jobseek.entity.Industry;
import com.jobseek.entity.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class CompanyRespDto {

    private String name;

    private String location;

    private Industry industry;

    private Size size;

    private Integer foundedYear;

    private String website;

    private String description;
}
