package com.jobseek.controller;

import com.jobseek.dto.JobApplicationDto;
import com.jobseek.dto.JobReqDto;
import com.jobseek.dto.RecruiterReqDto;
import com.jobseek.service.JobApplicationService;
import com.jobseek.service.CandidateService;
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
    public final JobApplicationService jobApplicationService;
    public final CandidateService candidateService;

    //add recruiter
    @PostMapping("/add/{userId}")
    public ResponseEntity<?> addRecruiter(@PathVariable long userId, @RequestBody RecruiterReqDto recruiterReqDto){
        return ResponseEntity.ok(recruiterService.addRecruiter(userId,recruiterReqDto));
    }
    //get Recruiter profile
    @GetMapping("/profile/{userId}")
    public ResponseEntity<?> getRecruiterProfile(@PathVariable long userId){
//        return ResponseEntity.ok(recruiterService.getRecruiterProfile(userId));
        return ResponseEntity.ok(userService.getUser(userId));
    }

    //get job
    @GetMapping("/job/{jobId}")
    public ResponseEntity<?> getJob(@PathVariable long jobId){
        return ResponseEntity.ok(jobService.getJob(jobId));
    }

    //add new job
    @PostMapping("/job/{userId}")
    public ResponseEntity<?> addJob(@PathVariable long userId, @RequestBody JobReqDto jobReqDto){
        return ResponseEntity.ok(jobService.addJob(userId,jobReqDto));
    }

    //update job
    @PutMapping("/job/{jobId}")
    public ResponseEntity<?> updateJob(@PathVariable long jobId, @RequestBody JobReqDto jobReqDto){
        return ResponseEntity.ok(jobService.updateJob(jobId,jobReqDto));
    }


    //delete job
    @DeleteMapping("/job/{jobId}")
    public ResponseEntity<?> deleteJob(@PathVariable long jobId){
        return ResponseEntity.ok(jobService.deleteJob(jobId));
    }

    //get all jobs
    @GetMapping("/jobs/{userId}")
    public ResponseEntity<?> getPostedJobs(@PathVariable long userId){
        return ResponseEntity.ok(jobService.getAllJobs(userId));
    }

    //get all application to the job
    @GetMapping("/job/application/{jobId}")
    public ResponseEntity<?> getCandidateApplications(@PathVariable long jobId){
        return ResponseEntity.ok(jobService.getJobApplications(jobId));
    }

    @PostMapping("/job/application/status")
    public ResponseEntity<?> getJobApplicationStatus(@RequestBody JobApplicationDto jobApplicationDto){
        return ResponseEntity.ok(jobApplicationService.getApplicationStatus(jobApplicationDto));
    }

    @PutMapping("/job/application/status")
    public ResponseEntity<?> updateJobApplicationStatus(@RequestBody JobApplicationDto jobApplicationDto){
        return ResponseEntity.ok(jobApplicationService.updateApplicationStatus(jobApplicationDto));
    }


    @GetMapping("/candidates")
    public ResponseEntity<?> getAllCandidates() {
        return ResponseEntity.ok(candidateService.getAllCandidates());
    }

    @GetMapping("/candidate/{userId}")
    public ResponseEntity<?> getCandidateProfile(@PathVariable long userId) {
//        return ResponseEntity.ok(candidateService.getCandidateProfile(userId));
        return ResponseEntity.ok(userService.getUser(userId));
    }

    @GetMapping("/candidates/skill")
    public ResponseEntity<?> searchCandidatesBySkill(@RequestParam String skill) {
        return ResponseEntity.ok(candidateService.searchCandidatesBySkill(skill));
    }

    @GetMapping("/candidates/experience")
    public ResponseEntity<?> searchByExperience(@RequestParam double minYears) {
        return ResponseEntity.ok(candidateService.searchCandidatesByExperience(minYears));
    }


}
