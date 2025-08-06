package com.jobseek.service;

import com.jobseek.dao.CandidateDao;
import com.jobseek.dao.JobApplicationDao;
import com.jobseek.dao.SkillDao;
import com.jobseek.dao.UserDao;
import com.jobseek.dto.*;
import com.jobseek.entity.*;
import com.jobseek.dto.CandidateProfileDto;
import com.jobseek.dto.CandidateReqDto;
import com.jobseek.dto.CandidateRespDto;
import com.jobseek.dto.SkillsReqDto;
import com.jobseek.entity.Candidate;
import com.jobseek.entity.Experience;
import com.jobseek.entity.Skill;
import com.jobseek.entity.User;
import com.jobseek.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class CandidateServiceImpl implements CandidateService {

    public final CandidateDao candidateDao;
    public final UserDao userDao;
    public final ModelMapper modelMapper;
    public final SkillDao skillDao;
    public final JobApplicationDao jobApplicationDao;

    @Override
    public CandidateRespDto createCandidate(CandidateReqDto candidateReqDto, long userId) {
        Candidate candidate = modelMapper.map(candidateReqDto, Candidate.class);
        User user = userDao.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("No user with passed userId"));
        candidate.setUser(user);
        return modelMapper.map(candidateDao.save(candidate), CandidateRespDto.class);
    }

    @Override
    public CandidateRespDto addSkills(Long userId, @NotNull SkillsReqDto cskill) {
        Candidate candidate = candidateDao.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Candidate not found"));
        Set<Skill> skills = new HashSet<>(skillDao.findAllById(cskill.getSkillIds()));
        candidate.getCskills().addAll(skills);
        return modelMapper.map(candidateDao.save(candidate), CandidateRespDto.class);
    }

    @Override
    public CandidateProfileDto getCandidateProfile(Long userId) {
        Candidate candidate = candidateDao.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Candidate not found"));
        candidate.getExperienceList();
        candidate.getEducationList();
        candidate.getCskills();
        return modelMapper.map(candidate,CandidateProfileDto.class);
    }

    @Override
    public List<JobRespDto> getAppliedJobs(Long userId) {
        Candidate candidate = candidateDao.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Candidate not found"));


        List<Job> jobs = new ArrayList<>();

        for (JobApplication application : candidate.getApplications()) {
            jobs.add(application.getJob());
        }

        List<JobRespDto> result = new ArrayList<>();
        for(Job job : jobs){
            JobRespDto dto = new JobRespDto();
            dto.setJobId(job.getJobId());
            dto.setTitle(job.getTitle());
            dto.setCompanyName(job.getCompanyName());
            dto.setYearOfExperience(job.getYearOfExperience());
            dto.setType(job.getType());
            dto.setDescription(job.getDescription());
            dto.setLocation(job.getLocation());
            Set<Skill> jskills = job.getJskills();

            for(Skill skill: jskills){
                SkillDto skillDto = new SkillDto(skill.getSkillId(),skill.getSkillName());
                dto.getJskills().add(skillDto);
            }
            result.add(dto);
        }
        return result;
    }

//            JobRespDto dto = new JobRespDto();
//            dto.setJobId(job.getJobId());
//            dto.setTitle(job.getTitle());
//            dto.setCompanyName(job.getCompanyName());
//            dto.setYearOfExperience(job.getYearOfExperience());
//            dto.setType(job.getType());
//            dto.setDescription(job.getDescription());
//            dto.setLocation(job.getLocation());
//            dto.setPostedDate(job.getPostedDate());
//
//            // Convert skills manually to SkillDto
//            Set<SkillDto> skillDtos = job.getJskills().stream()
//                    .map(skill -> {
//                        SkillDto skillDto = new SkillDto();
//                        skillDto.setSkillId(skill.getSkillId());
//                        skillDto.setSkillName(skill.getSkillName());
//                        return skillDto;
//                    })
//                    .collect(Collectors.toSet());
//
//            dto.setJskills(skillDtos);
//
//            result.add(dto);
//        }
//
//        return result;
//    }

//    @Override
//    public List<JobRespDto> getAppliedJobs(Long userId) {
//        Candidate candidate = candidateDao.findWithApplicationsAndJobsAndSkills(userId)
//                .orElseThrow(() -> new ResourceNotFoundException("Candidate not found"));
//
//        return candidate.getApplications()
//                .stream()
//                .map(JobApplication::getJob)
//                .map(job -> modelMapper.map(job, JobRespDto.class))
//                .toList();
//    }


    @Override
    public List<CandidateRespDto> searchCandidatesBySkill(String skillName) {
        List<Candidate> candidates = candidateDao.findCandidatesBySkillName(skillName);
        return candidates.stream()
                .map(candidate -> modelMapper.map(candidate, CandidateRespDto.class))
                .toList();
    }

    @Override
    public List<CandidateRespDto> searchCandidatesByExperience(double minYears) {
        List<Candidate> candidates = candidateDao.findAll();  // fetch all

        return candidates.stream()
                .filter(candidate -> calculateTotalExperienceInYears(candidate.getExperienceList()) >= minYears)
                .map(candidate -> modelMapper.map(candidate, CandidateRespDto.class))
                .toList();
    }


    private double calculateTotalExperienceInYears(List<Experience> experienceList) {
        double totalMonths = 0;

        for (Experience exp : experienceList) {
            LocalDate start = exp.getStartDate();
            LocalDate end = (exp.getEndDate() != null) ? exp.getEndDate() : LocalDate.now();

            if (start != null && end != null && !end.isBefore(start)) {
                Period period = Period.between(start, end);
                int months = period.getYears() * 12 + period.getMonths();
                totalMonths += months;
            }
        }
        double totalYears = totalMonths / 12.0;
        return Math.round(totalYears * 100.0) / 100.0;

    }

    @Override
    public CandidateRespDto updateCandidateProfile(long userId, CandidateReqDto dto) {
        Candidate candidate = candidateDao.findById(userId)
                .orElseThrow(() -> new RuntimeException("Candidate not found with ID: " + userId));

        // Update fields
        if (dto.getName() != null) candidate.setName(dto.getName());
        if (dto.getDateOfBirth() != null) candidate.setDateOfBirth(dto.getDateOfBirth());
        if (dto.getMobileNo() != null) candidate.setMobileNo(dto.getMobileNo());
        if (dto.getLinkedIn() != null) candidate.setLinkedIn(dto.getLinkedIn());
        if (dto.getGitHub() != null) candidate.setGitHub(dto.getGitHub());

        Candidate updatedCandidate = candidateDao.save(candidate);

        return mapToResponseDto(updatedCandidate);
    }

    private CandidateRespDto mapToResponseDto(Candidate candidate) {
        CandidateRespDto dto = new CandidateRespDto();
        dto.setUserId(candidate.getUserId());
        dto.setName(candidate.getName());
        dto.setDateOfBirth(candidate.getDateOfBirth());
        dto.setMobileNo(candidate.getMobileNo());
        dto.setLinkedIn(candidate.getLinkedIn());
        dto.setGitHub(candidate.getGitHub());
        return dto;
    }
}       