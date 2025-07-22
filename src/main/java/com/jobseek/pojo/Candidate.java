package com.jobseek.pojo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "candidates")
public class Candidate {
    @Id
    @Column(name = "user_id")
    private long userId;

    private String name;
    @Column(name ="date_of_birth")
    private LocalDate dateOfBirth;
    @Column(name="mobile_no")
    private String mobileNo;
    @Column(name = "LinkedIn")
    private String linkedIn;
    @Column(name = "GitHub")
    private String gitHub;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private List<Education> educationList = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private List<Experience> experienceList = new ArrayList<>();

    public Candidate(String name, LocalDate dateOfBirth, String mobileNo, String linkedIn, String gitHub) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.mobileNo = mobileNo;
        this.linkedIn = linkedIn;
        this.gitHub = gitHub;
    }
}
