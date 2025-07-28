package com.jobseek.dto;

import com.jobseek.entity.Degree;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
public class EductionReqDto {

    @NotNull(message = "Degree is required")
    private Degree degree;
    @NotBlank(message = "grade is required")
    private String grade;
    @Past
    private LocalDate startYear;
    @Past
    private LocalDate endYear;
    @NotBlank(message = "University cannot be blank")
    private String university;
}
