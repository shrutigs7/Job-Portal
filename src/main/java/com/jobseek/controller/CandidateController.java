package com.jobseek.controller;

import com.jobseek.dto.*;
import com.jobseek.service.CandidateService;
import com.jobseek.service.EducationService;
import com.jobseek.service.ExperienceService;
import com.jobseek.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/candidate")
@AllArgsConstructor
public class CandidateController {

    public final CandidateService candidateService;

    public final EducationService educationService;

    public final ExperienceService experienceService;

    public final UserService userService;

    @PostMapping("/add/{userId}")
    public ResponseEntity<CandidateRespDto> addCandidate(@RequestBody CandidateReqDto dto, @PathVariable long userId) {
        return ResponseEntity.ok(candidateService.createCandidate(dto, userId));
    }

    @PostMapping("/add-education/{userId}")
    public ResponseEntity<?> addCandidateEducation(@PathVariable long userId,@RequestBody EducationReqDto eductionReqDto){
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

    @GetMapping("/profile/{userId}")
    public ResponseEntity<?> getCandidateProfile(@PathVariable long userId) {
//        return ResponseEntity.ok(candidateService.getCandidateProfile(userId));
        return ResponseEntity.ok(userService.getUser(userId));
    }

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
