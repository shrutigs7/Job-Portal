package com.jobseek.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Year;

@Entity
@Table(name = "companies")
@Getter
@Setter
@NoArgsConstructor
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_id")
    private long companyId;
    private String name;
    private String location;
    @Enumerated(value = EnumType.STRING)
    private Industry industry;
    @Enumerated(value = EnumType.STRING)
    private Size size;
    private Year foundedYear;
    private String website;
    private String description;

    public Company(String name, String location, Industry industry, Size size, Year foundedYear, String website, String description) {
        this.name = name;
        this.location = location;
        this.industry = industry;
        this.size = size;
        this.foundedYear = foundedYear;
        this.website = website;
        this.description = description;
    }
}
