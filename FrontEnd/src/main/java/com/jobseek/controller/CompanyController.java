package com.jobseek.controller;

import com.jobseek.dto.ApiResponse;
import com.jobseek.dto.CompanyReqDto;
import com.jobseek.service.CompanyService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/recruiter")
@AllArgsConstructor
public class CompanyController {

    public final CompanyService companyService;

//    @PostMapping("/add-company")
//    public ResponseEntity<?> addCompany(@RequestBody CompanyReqDto companyReqDto){
//            companyService.addCompany(companyReqDto);
//            return ResponseEntity.ok(new ApiResponse("Company Added"));
//    }

}
