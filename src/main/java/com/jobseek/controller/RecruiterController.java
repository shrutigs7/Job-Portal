package com.jobseek.controller;

import com.jobseek.dto.JobReqDto;
import com.jobseek.dto.RecruiterReqDto;
import com.jobseek.service.JobService;
import com.jobseek.service.RecruiterService;
import com.jobseek.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/recruiter")
@AllArgsConstructor
public class RecruiterController {

    public final RecruiterService recruiterService;
    public final UserService userService;
    public final JobService jobService;

    @PostMapping("/add")
    public ResponseEntity<?> addRecruiter(@RequestParam long userId, @RequestBody RecruiterReqDto recruiterReqDto){
        return ResponseEntity.ok(recruiterService.addRecruiter(userId,recruiterReqDto));
    }

    @GetMapping("/profile/{userId}")
    public ResponseEntity<?> getRecruiterProfile(@PathVariable long userId){
//        return ResponseEntity.ok(recruiterService.getRecruiterProfile(userId));
        return ResponseEntity.ok(userService.getUser(userId));
    }

    @PostMapping("/job/{userId}")
    public ResponseEntity<?> addJob(@PathVariable long userId, @RequestBody JobReqDto jobReqDto){
        return ResponseEntity.ok(jobService.addJob(userId,jobReqDto));
    }

    @PutMapping("/job/{jobId}")
    public ResponseEntity<?> updateJob(@PathVariable long jobId, @RequestBody JobReqDto jobReqDto){
        return ResponseEntity.ok(jobService.updateJob(jobId,jobReqDto));
    }

    @DeleteMapping("/job/{jobId}")
    public ResponseEntity<?> deleteJob(@PathVariable long jobId){
        return ResponseEntity.ok(jobService.deleteJob(jobId));
    }
}
