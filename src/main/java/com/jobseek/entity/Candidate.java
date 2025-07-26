package com.jobseek.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


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
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;
    @Column(name = "mobile_no")
    private String mobileNo;
    @Column(name = "LinkedIn")
    private String linkedIn;
    @Column(name = "GitHub")
    private String gitHub;
    @UpdateTimestamp
    private LocalDate lastUpdated;


    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
//    @ToString.Exclude
//    @JsonIgnore
    private User user;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private List<Education> educationList = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private List<Experience> experienceList = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "candidate_skill",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id")
    )
    private Set<Skill> cskills = new HashSet<>();

    public Candidate(String name, LocalDate dateOfBirth, String mobileNo, String linkedIn, String gitHub, Set<Skill> skills) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.mobileNo = mobileNo;
        this.linkedIn = linkedIn;
        this.gitHub = gitHub;
        this.cskills = skills;
    }
}
