package com.jobseek.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "recruiters")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Recruiter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long userId;
    private String name;
    private String mobNumber;
    private String title;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
//    @ToString.Exclude
//    @JsonIgnore
    private User user;

    @OneToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @OneToMany(mappedBy = "recruiter",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Job> jobs = new ArrayList<>();


    public Recruiter(String name, String mobNumber, String title) {
        name = name;
        this.mobNumber = mobNumber;
        this.title = title;

    }
}
