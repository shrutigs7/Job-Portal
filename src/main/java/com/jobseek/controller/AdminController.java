package com.jobseek.controller;

import com.jobseek.exception.ResourceNotFoundException;
import com.jobseek.service.AdminService;
import com.jobseek.service.JobService;
import com.jobseek.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
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
        try {
            return ResponseEntity.ok().body(adminService.getAdmin(id));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(e.getMessage());
        }
    }

    @GetMapping("/jobs")
    public ResponseEntity<?> getJobs() {
        try {
            return ResponseEntity.ok(jobService.getAllJobs());
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(e.getMessage());
        }
    }

    @GetMapping("/job-count")
    public ResponseEntity<?> getJobsCount() {
        try {
            return ResponseEntity.ok(jobService.getAllJobs().size());
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(e.getMessage());
        }
    }

    @GetMapping("/users-count")
    public ResponseEntity<?> getUsersCount() {
        try {
            return ResponseEntity.ok(userService.getAllUsers().size());
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(e.getMessage());
        }
    }

    @GetMapping("/users")
    public ResponseEntity<?> getUsers() {
        try {
            return ResponseEntity.ok(userService.getAllUsers());
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(e.getMessage());
        }
    }

}
