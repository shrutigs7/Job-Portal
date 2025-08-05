package com.jobseek.dto;

import com.jobseek.entity.ApplicationStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JobApplicationDto {

    private long userId;
    private long jobId;
    private ApplicationStatus status;
}
