package com.jobseek.controller;

import com.jobseek.dto.*;
import com.jobseek.service.*;
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

    public final JobApplicationService jobApplicationService;

    @PostMapping("/{userId}")
    public ResponseEntity<CandidateRespDto> addCandidate(@RequestBody CandidateReqDto dto, @PathVariable long userId) {
        return ResponseEntity.ok(candidateService.createCandidate(dto, userId));
    }

    @PostMapping("/education/{userId}")
    public ResponseEntity<?> addCandidateEducation(@PathVariable long userId,@RequestBody EducationReqDto eductionReqDto){
        return ResponseEntity.ok(educationService.addEducation(eductionReqDto,userId));
    }

    @PostMapping("/experience/{userId}")
    public ResponseEntity<?> addCandidateExperience(@PathVariable long userId,@RequestBody ExperienceReqDto experienceReqDto){
        return ResponseEntity.ok(experienceService.addExperience(userId,experienceReqDto));
    }

    @PostMapping("/skills/{userId}")
    public ResponseEntity<?> addCandidateSkills(@PathVariable long userId, @RequestBody SkillsReqDto cskill){
        return ResponseEntity.ok(candidateService.addSkills(userId,cskill));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getCandidateProfile(@PathVariable long userId) {
//        return ResponseEntity.ok(candidateService.getCandidateProfile(userId));
        return ResponseEntity.ok(userService.getUser(userId));
    }

    @PostMapping("/job")
    public ResponseEntity<?> applyForJob(@RequestBody JobApplicationDto jobApplicationDto){
        return ResponseEntity.ok(jobApplicationService.addJobApplication(jobApplicationDto));
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
