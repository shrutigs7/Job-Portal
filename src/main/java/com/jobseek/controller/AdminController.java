package com.jobseek.controller;

import com.jobseek.service.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController {

    private final AdminService service;

    @GetMapping("/home/{id}")
    public ResponseEntity<?> getAdminDetails(@PathVariable long id){
        return ResponseEntity.ok().body(service.getAdmin(id));
    }
}
