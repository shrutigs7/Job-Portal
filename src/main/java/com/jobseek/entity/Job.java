package com.jobseek.entity;

import com.fasterxml.jackson.databind.annotation.EnumNaming;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.util.HashSet;
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
    @Enumerated(EnumType.STRING)
    private JobType type;
    private String description;
    private String location;
    @Column(name = "posted_date")
    private LocalDate postedDate;
    @UpdateTimestamp
    private LocalDate lastUpdated;
    @Column(name = "is_active")
    private boolean isActive;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Recruiter recruiter;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "job_skill",
            joinColumns = @JoinColumn(name = "job_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id")
    )
    private Set<Skill> jskills = new HashSet<Skill>();

    public Job(String title, JobType type, String description, String location, LocalDate postedDate, boolean isActive, Recruiter recruiter, Set<Skill> skills) {
        this.title = title;
        this.type = type;
        this.description = description;
        this.location = location;
        this.postedDate = postedDate;
        this.isActive = isActive;
        this.recruiter = recruiter;
        this.jskills = skills;
    }
}
