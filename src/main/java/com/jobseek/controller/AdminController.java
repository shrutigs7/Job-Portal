package com.jobseek.controller;

import com.jobseek.exception.ResourceNotFoundException;
import com.jobseek.service.AdminService;
import com.jobseek.service.JobService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController {

    private final AdminService adminService;
    private final JobService jobService;

    @GetMapping("/home/{id}")
    public ResponseEntity<?> getAdminDetails(@PathVariable long id){
        try {
            return ResponseEntity.ok().body(adminService.getAdmin(id));
        }catch (ResourceNotFoundException e){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(e.getMessage());
        }
    }

    @GetMapping("/jobs")
    public ResponseEntity<?> getAllJobs(){
        try {
            return ResponseEntity.ok(jobService.getAllJobs());
        }catch (ResourceNotFoundException e){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(e.getMessage());
        }
    }
}
