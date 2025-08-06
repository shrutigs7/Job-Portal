package com.jobseek.dto;

import com.jobseek.entity.Industry;
import com.jobseek.entity.Size;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecruiterReqDto {

    @NotBlank(message = "Recruiter name is required")
    private String name;

    @NotBlank(message = "Contact information is required")
    private String mobNumber;

    @NotBlank(message = "Role of Recruiter is required")
    private String title;

    @NotBlank(message = "Company companyName is required")
    private String companyName;

    @NotBlank(message = "Location is required")
    private String location;

    @NotNull(message = "Industry is required")
    private Industry industry;

    @NotNull(message = "Size is required")
    private Size size;

    @Min(value = 1800, message = "Year must be realistic")
    @Max(value = 2025, message = "Year can't be in the future")
    private Integer foundedYear;

    @NotBlank(message = "Website is required")
    @Pattern(regexp = "^(http[s]?://)?[\\w.-]+(\\.[\\w.-]+)+[/#?]?.*$", message = "Invalid website URL")
    private String website;

    private String description;
}
