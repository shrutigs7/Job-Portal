package com.jobseek.service;

import com.jobseek.dao.CandidateDao;
import com.jobseek.dao.SkillDao;
import com.jobseek.dao.UserDao;
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
        return modelMapper.map(candidate, CandidateProfileDto.class);
    }

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
                totalMonths += java.time.temporal.ChronoUnit.MONTHS.between(start, end);
            }
        }
        return totalMonths / 12.0;

    }
}
