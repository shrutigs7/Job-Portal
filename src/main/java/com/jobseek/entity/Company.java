package com.jobseek.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Proxy;

@Entity
@Table(name = "companies")
@Getter
@Setter
@NoArgsConstructor
@Proxy(lazy = false) // Ensures eager loading, avoids lazy loading proxy errors
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_id")
    private long companyId;
    @Column(name = "company_name")
    private String companyName;
    private String location;

    @Enumerated(EnumType.STRING)
    private Industry industry;

    @Enumerated(EnumType.STRING)
    private Size size;

    @Column(name = "founded_year")
    private Integer foundedYear; // changed from java.time.Year for Hibernate compatibility

    private String website;
    private String description;

    // Optional: All-args constructor (except ID)
    public Company(String companyName, String location, Industry industry, Size size,
                   Integer foundedYear, String website, String description) {
        this.companyName = companyName;
        this.location = location;
        this.industry = industry;
        this.size = size;
        this.foundedYear = foundedYear;
        this.website = website;
        this.description = description;
    }
}
