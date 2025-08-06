package com.jobseek.controller;

import com.jobseek.dto.EducationReqDto;
import com.jobseek.service.EducationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/candidate/education")
@AllArgsConstructor
public class EducationController {

    public final EducationService educationService;

    @PutMapping("/{userId}/{eduId}")
    public ResponseEntity<?> updateEducation(@PathVariable long userId,@PathVariable long eduId,
                                             @RequestBody EducationReqDto educationReqDto){
       return ResponseEntity.ok(educationService.updateEducation(educationReqDto,userId,eduId));
    }

    @DeleteMapping("/{userId}/{eduId}")
    public ResponseEntity<?> deleteEducation(@PathVariable long userId,@PathVariable long eduId){
        return ResponseEntity.ok(educationService.deleteEducation(userId,eduId));
    }
}
