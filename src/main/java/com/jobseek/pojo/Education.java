package com.jobseek.pojo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "educations")
@NoArgsConstructor
public class Education {

    @Id
    @Column(name = "edu_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long eduId;

//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private Candidate candidate;

    @Enumerated(EnumType.STRING)
    private Degree degree;
    private String grade;
    @Column(name = "start_year")
    private LocalDate startYear;
    @Column(name = "end_year")
    private LocalDate endYear;
    private String university;

    public Education(Degree degree, String grade, LocalDate startYear, LocalDate endYear, String university) {
        this.degree = degree;
        this.grade = grade;
        this.startYear = startYear;
        this.endYear = endYear;
        this.university = university;
    }
}
