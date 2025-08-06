package com.jobseek.controller;


import com.jobseek.dto.ExperienceReqDto;
import com.jobseek.service.ExperienceService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/candidate/experience")
@AllArgsConstructor
public class ExperienceController {
    public final ExperienceService experienceService;

    @PutMapping("/{userId}/{expId}")
    public ResponseEntity<?> updateEducation(@PathVariable long userId, @PathVariable long expId,
                                             @RequestBody ExperienceReqDto experienceReqDto){
        return ResponseEntity.ok(experienceService.updateExperience(experienceReqDto,userId,expId));
    }

    @DeleteMapping("/{userId}/{expId}")
    public ResponseEntity<?> deleteEducation(@PathVariable long userId,@PathVariable long expId){
        return ResponseEntity.ok(experienceService.deleteExperience(userId,expId));
    }

}
