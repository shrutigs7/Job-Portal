package com.jobseek.dto;

import com.jobseek.entity.*;
import lombok.*;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailsDto {

    private long userId;
    private String password;
    private String email;
    private Role role;

}
