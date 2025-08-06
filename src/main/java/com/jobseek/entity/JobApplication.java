package com.jobseek.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "job_applications")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class JobApplication {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "candidate_id")
        private Candidate candidate;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "job_id")
        private Job job;

        @Column(name = "applied_date")
        private LocalDate appliedDate = LocalDate.now();

        @Enumerated(EnumType.STRING)
        private ApplicationStatus status = ApplicationStatus.APPLIED;

    }

