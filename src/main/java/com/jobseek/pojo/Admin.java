package com.jobseek.pojo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Admin")
@Getter
@Setter
@NoArgsConstructor


public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long userId;
    private String name;
    private String mobNumber;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    public Admin( String name, String mobNumber) {

        this.name = name;
        this.mobNumber = mobNumber;

    }
}
