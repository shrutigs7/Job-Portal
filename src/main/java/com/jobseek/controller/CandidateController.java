package com.jobseek.controller;

import com.jobseek.dto.*;
import com.jobseek.entity.Skill;
import com.jobseek.service.CandidateService;
import com.jobseek.service.EducationService;
import com.jobseek.service.ExperienceService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;


@RestController
@RequestMapping("/candidate")
@AllArgsConstructor
public class CandidateController {

    public final CandidateService candidateService;

    public final EducationService educationService;

    public final ExperienceService experienceService;

    @PostMapping("/add/{userId}")
    public ResponseEntity<CandidateRespDto> addCandidate(@RequestBody CandidateReqDto dto, @PathVariable long userId) {
        return ResponseEntity.ok(candidateService.createCandidate(dto, userId));
    }

    @PostMapping("/add-education/{userId}")
    public ResponseEntity<?> addCandidateEducation(@PathVariable long userId,@RequestBody EductionReqDto eductionReqDto){
        return ResponseEntity.ok(educationService.addEducation(eductionReqDto,userId));
    }

    @PostMapping("/add-experience/{userId}")
    public ResponseEntity<?> addCandidateExperience(@PathVariable long userId,@RequestBody ExperienceReqDto experienceReqDto){
        return ResponseEntity.ok(experienceService.addExperience(userId,experienceReqDto));
    }

    @PostMapping("/add-skills/{userId}")
    public ResponseEntity<?> addCandidateSkills(@PathVariable long userId, @RequestBody SkillsReqDto cskill){
        return ResponseEntity.ok(candidateService.addSkills(userId,cskill));
    }

//    @GetMapping("/{userId}")
//    public ResponseEntity<CandidateRespDto> getCandidate(@PathVariable long userId) {
//        return null;//ResponseEntity.ok(candidateService.getCandidateById(userId));
//    }

//    @GetMapping
//    public ResponseEntity<List<CandidateRespDto>> getAllCandidates() {
//        return ResponseEntity.ok(candidateService.getAllCandidates());
//    }
//
//    @PutMapping("/{userId}")
//    public ResponseEntity<CandidateRespDto> updateCandidate(@PathVariable long userId, @RequestBody CandidateReqDto dto) {
//        return ResponseEntity.ok(candidateService.updateCandidate(userId, dto));
//    }

}
