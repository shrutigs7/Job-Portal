package com.jobseek.controller;

import com.jobseek.service.SkillService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/skill")
@AllArgsConstructor
public class SkillController {

    public final SkillService skillService;

    @GetMapping("/get")
    public ResponseEntity<?> getAllSkills(){
        return ResponseEntity.ok(skillService.getAllSkills());
    }

}
