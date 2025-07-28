package com.jobseek.controller;

import com.jobseek.dto.CandidateReqDto;
import com.jobseek.dto.CandidateRespDto;
import com.jobseek.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class CandidateController {
    @Autowired
    private CandidateService candidateService;

    @PostMapping("/{userId}")
    public ResponseEntity<CandidateRespDto> createCandidate(@RequestBody CandidateReqDto dto, @PathVariable long userId) {
        CandidateRespDto created = candidateService.createCandidate(dto, userId);
        return ResponseEntity.ok(created);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<CandidateRespDto> getCandidate(@PathVariable long userId) {
        return ResponseEntity.ok(candidateService.getCandidateById(userId));
    }

    @GetMapping
    public ResponseEntity<List<CandidateRespDto>> getAllCandidates() {
        return ResponseEntity.ok(candidateService.getAllCandidates());
    }

    @PutMapping("/{userId}")
    public ResponseEntity<CandidateRespDto> updateCandidate(@PathVariable long userId, @RequestBody CandidateReqDto dto) {
        return ResponseEntity.ok(candidateService.updateCandidate(userId, dto));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteCandidate(@PathVariable long userId) {
        candidateService.deleteCandidate(userId);
        return ResponseEntity.noContent().build();
    }
}
