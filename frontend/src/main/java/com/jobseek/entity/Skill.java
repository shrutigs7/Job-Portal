package com.jobseek.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "skills")
@Getter
@Setter
@ToString(callSuper = true)


public class Skill {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "skill_id")
   private long skillId;
   @Column(name = "skill_name")
   private String skillName;

   @ManyToMany(mappedBy = "jskills", fetch = FetchType.LAZY)
   @JsonIgnore
   private Set<Job> jobs = new HashSet<>();

   @ManyToMany(mappedBy = "cskills", fetch = FetchType.LAZY)
   @JsonIgnore
   private Set<Candidate> candidates = new HashSet<>();

   public Skill() {

   }

   public Skill(long skillId, String skillName) {
      this.skillId = skillId;
      this.skillName = skillName;
   }
}
