package com.jobseek.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@Table(name = "jobs")
@Setter
@Getter
@NoArgsConstructor
@ToString
public class Job {

    @Id
    @Column(name = "job_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long jobId;

    private String title;
    @Column(name = "company-name")
    private String companyName;
    @Column(name = "year-of-experience")
    private int yearOfExperience;
    @Enumerated(EnumType.STRING)
    private JobType type;
    private String description;
    private String location;
    @Column(name = "posted_date")
    private LocalDate postedDate;
    @UpdateTimestamp
    private LocalDate lastUpdated;
    @Column(name = "is_active", columnDefinition = "TINYINT")
    private boolean isActive;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @JsonIgnore
    private Recruiter recruiter;

    @JsonManagedReference
    @ManyToMany
    @JoinTable(
            name = "job_skill",
            joinColumns = @JoinColumn(name = "job_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id")
    )
    @JsonIgnore
    private Set<Skill> jskills = new HashSet<>();

    @OneToMany(mappedBy = "job", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<JobApplication> applications = new ArrayList<>();


    public Job(String title, JobType type, String description, String location, Recruiter recruiter, Set<Skill> skills) {
        this.title = title;
        this.type = type;
        this.description = description;
        this.location = location;
        this.postedDate = LocalDate.now();
        this.isActive = true;
        this.recruiter = recruiter;
        this.jskills = skills;
    }
}
