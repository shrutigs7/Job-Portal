package com.jobseek.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Users")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @Column(name = "user_id")
    private long userId;
    private String password;
    private String email;
    @Enumerated(value = EnumType.STRING)
    private Role role;
    @Column(name = "is_active")
    private boolean isActive;

    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
    @ToString.Exclude
    @JsonIgnore
    private Candidate candidate;

    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
    @ToString.Exclude
    @JsonIgnore
    private Admin admin;

    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
    @ToString.Exclude
    @JsonIgnore
    private Recruiter recruiter;


    public User(long userId, String password, String email, Role role) {
        this.userId = userId;
        this.password = password;
        this.email = email;
        this.role = role;
        this.isActive = true;
    }
}
