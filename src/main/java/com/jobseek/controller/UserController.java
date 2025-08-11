package com.jobseek.controller;

import com.jobseek.security.JwtUtils;
import com.jobseek.dto.AuthResponse;
import com.jobseek.dto.SignInDto;
import com.jobseek.dto.SignUpDto;
import com.jobseek.dto.UserDetailsDto;
import com.jobseek.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    public final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> signIn(@RequestBody SignInDto signInDto) {

        // 1. Authenticate credentials (email/password) with Spring Security
        Authentication authToken = new UsernamePasswordAuthenticationToken(
                signInDto.getEmail(), signInDto.getPassword());

        Authentication authResult = authenticationManager.authenticate(authToken);
        // 2. Generate JWT from authenticated principal (e.g. email/username)
        String token = jwtUtils.generateJwtToken(authResult);

        Long userId = userService.getUserByEmail(signInDto.getEmail())
                .getUserId();

        // 3. Return token (for example, in body via an AuthResp DTO)
        return ResponseEntity.ok(new AuthResponse(userId.toString(), token));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody SignUpDto signUpDto) {

        UserDetailsDto newUser = userService.addUser(signUpDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

}
