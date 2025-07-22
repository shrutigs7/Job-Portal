package com.jobseek.pojo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Entity
@Table(name = "Skill")
@Getter
@Setter
@ToString(callSuper = true)


public class Skill {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "skill_id")
   public long skillId;
   @Column(name = "skill_name")
   public String skillName;


   public Skill(long skillId, String skillName) {
      this.skillId = skillId;
      this.skillName = skillName;
   }
}
