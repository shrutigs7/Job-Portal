package com.jobseek.service;

import com.jobseek.dao.JobDao;
import com.jobseek.dao.RecruiterDao;
import com.jobseek.dao.SkillDao;
import com.jobseek.dto.ApiResponse;
import com.jobseek.dto.CandidateProfileDto;
import com.jobseek.dto.JobReqDto;
import com.jobseek.dto.JobRespDto;
import com.jobseek.entity.*;
import com.jobseek.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
@AllArgsConstructor
public class JobServiceImpl implements JobService {

    private final JobDao jobDao;
    private final ModelMapper modelMapper;
    private final RecruiterDao recruiterDao;
    private final SkillDao skillDao;
    private final CandidateService candidateService;

    @Override
    public List<JobRespDto> getAllJobs() {
        List<Job> jobList = Optional.of(jobDao.findAll())
                .filter(jobs -> !jobs.isEmpty())
                .orElseThrow(() -> new ResourceNotFoundException("No jobs yet registered"));
        return jobList
                .stream()
                .filter(Job::isActive)
                .map(jobs -> modelMapper.map(jobs,JobRespDto.class))
                .toList();
    }

    @Override
    public List<JobRespDto> getAllJobs(Long userId) {
        Recruiter recruiter = recruiterDao.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("No jobs yet registered"));
        List<Job> jobList = Optional.of(jobDao.findAllByRecruiterUserIdAndIsActiveTrue(userId))
                .filter(jobs -> !jobs.isEmpty())
                .orElseThrow(() -> new ResourceNotFoundException("No jobs yet registered"));

        return jobList.stream()
                .map(job -> modelMapper.map(job,JobRespDto.class))
                .toList();
    }

    @Override
    public Job addJob(Long userId, JobReqDto jobReqDto) {
        Job job = modelMapper.map(jobReqDto, Job.class);
        job.setRecruiter(recruiterDao.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("no recruiter record")));
        job.setActive(true);
        job.setPostedDate(LocalDate.now());

        job.setJskills(setJSkills(jobReqDto));
        return jobDao.save(job);
    }

    @Override
    public JobRespDto updateJob(Long jobId, JobReqDto jobReqDto) {
        Job job = jobDao.findById(jobId)
                .orElseThrow(() -> new ResourceNotFoundException("Job not found with id "));
        modelMapper.map(jobReqDto, job);

        job.setJskills(setJSkills(jobReqDto));
        jobDao.save(job);
        return modelMapper.map(job, JobRespDto.class);
    }

    @Override
    public ApiResponse deleteJob(Long jobId) {
        Job job = jobDao.findById(jobId)
                .orElseThrow(() -> new ResourceNotFoundException("Job not Found "));
        job.setActive(false);
        jobDao.save(job);
        return new ApiResponse("Job Deleted");
    }

    @Override
    public List<CandidateProfileDto> getJobApplications(Long jobId) {
        return jobDao.findById(jobId)
                .orElseThrow(() -> new ResourceNotFoundException("Skill not found with id "))
                .getApplications()
                .stream()
                .map(JobApplication::getCandidate)
                .map(candidate ->candidateService.getCandidateProfile(candidate.getUserId()))
                .toList();
    }

    @Override
    public List<Job> searchJobsBySkillName(String skillName) {
        return jobDao.findJobsBySkillName(skillName);
    }

    @Override
    public List<Job> getJobsByYearOfExperience(int yearOfExperience) {
        return jobDao.findByYearOfExperienceLessThanEqual(yearOfExperience);
    }




    private Set<Skill> setJSkills(JobReqDto jobReqDto){
        Set<Skill> skills = new HashSet<>();
        for (Long skillId : jobReqDto.getJskills()) {
            Skill skill = skillDao.findById(skillId)
                    .orElseThrow(() -> new ResourceNotFoundException("Skill not found with id "));
            skills.add(skill);
        }
        return skills;
    }
}

