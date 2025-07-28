package com.jobseek.controller;

import com.jobseek.service.AdminService;
import com.jobseek.service.JobService;
import com.jobseek.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController {

    private final AdminService adminService;
    private final JobService jobService;
    private final UserService userService;

    @GetMapping("/home/{id}")
    public ResponseEntity<?> getAdminDetails(@PathVariable long id) {
        return ResponseEntity.ok().body(adminService.getAdmin(id));
    }

    @GetMapping("/jobs")
    public ResponseEntity<?> getJobs() {
        return ResponseEntity.ok(jobService.getAllJobs());
    }

    @GetMapping("/job-count")
    public ResponseEntity<?> getJobsCount() {
        return ResponseEntity.ok(jobService.getAllJobs().size());
    }

    @GetMapping("/users-count")
    public ResponseEntity<?> getUsersCount() {
        return ResponseEntity.ok(userService.getAllUsers().size());
    }

    @GetMapping("/users")
    public ResponseEntity<?> getUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/user-details/{userId}")
    public ResponseEntity<?> getUserDetails(@PathVariable long userId){
        return ResponseEntity.ok(userService.getUser(userId));
    }

}
