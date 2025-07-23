package com.jobseek.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Admin")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
//    @ToString.Exclude
//    @JsonIgnore
    private User user;



    public Admin( String name, String mobNumber) {

        this.name = name;
        this.mobNumber = mobNumber;

    }
}
