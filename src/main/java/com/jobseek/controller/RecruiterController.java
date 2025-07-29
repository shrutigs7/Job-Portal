package com.jobseek.controller;

import com.jobseek.dto.RecruiterReqDto;
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

    @PostMapping("/add")
    public ResponseEntity<?> addRecruiter(@RequestParam long userId, @RequestBody RecruiterReqDto recruiterReqDto){
        return ResponseEntity.ok(recruiterService.addRecruiter(userId,recruiterReqDto));
    }

    @GetMapping("/profile/{userId}")
    public ResponseEntity<?> getRecruiterProfile(@PathVariable long userId){
//        return ResponseEntity.ok(recruiterService.getRecruiterProfile(userId));
        return ResponseEntity.ok(userService.getUser(userId));
    }

}
