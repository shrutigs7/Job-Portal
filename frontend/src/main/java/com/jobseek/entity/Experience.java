package com.jobseek.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.Period;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "experiences")
public class Experience {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exp_id")
    private long expId;

//    @ManyToOne
//    @JoinColumn(companyName = "user_id")
//    private Candidate candidate;

    private String role;
    @Column(name = "company_name")
    private String companyName;
    @Column(name = "start_date")
    private LocalDate startDate;
    @Column(name = "end_date")
    private LocalDate endDate;
    @Column(name = "experience_in_years")
    private float expInYears;
    private String description;

    @PrePersist
    @PreUpdate
    public void calcTotalExperience(){
        if(startDate != null && endDate != null){
            int years = Period.between(startDate, endDate).getYears();
            int months = Period.between(startDate, endDate).getMonths();
            this.expInYears = years + (months / 12.0f);
        }
        else {
            this.expInYears=0L;
        }
    }

    public Experience(String role, String companyName, LocalDate startDate, LocalDate endDate, String description) {
        this.role = role;
        this.companyName = companyName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
    }
}
