package com.jobseek.controller;

import com.jobseek.dto.SignInDto;
import com.jobseek.dto.SignUpDto;
import com.jobseek.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@AllArgsConstructor
public class UserController {

    public final UserService userService;

    @PostMapping("/signin")
    public ResponseEntity<?> signIn(@RequestBody SignInDto signInDto){
        return ResponseEntity.ok(userService.getUser(signInDto));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignUpDto signUpDto){
        return ResponseEntity.ok(userService.addUser(signUpDto));
    }


}
