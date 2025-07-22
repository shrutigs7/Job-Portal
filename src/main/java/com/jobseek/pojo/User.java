package com.jobseek.pojo;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Users")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class User {

    @Id
    @Column(name = "user_id")
    public long userId;
    public String password;
    public String email;
    @Enumerated(value = EnumType.STRING)
    public Role role;
    @Column(name = "is_active")
    public boolean isActive;

    public User(long userId, String password, String email, Role role) {
        this.userId = userId;
        this.password = password;
        this.email = email;
        this.role = role;
        this.isActive = true;
    }
}
