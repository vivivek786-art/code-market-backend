package com.baalvionService.JobPortalApplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baalvionService.JobPortalApplication.entities.Application;
import com.baalvionService.JobPortalApplication.entities.Job;
import com.baalvionService.JobPortalApplication.entities.User;
import com.baalvionService.JobPortalApplication.enums.ApplicationStatus;
import com.baalvionService.JobPortalApplication.repositories.ApplicationRepository;
import com.baalvionService.JobPortalApplication.repositories.JobRepository;
import com.baalvionService.JobPortalApplication.repositories.UserRepository;

@Service
public class ApplicationService {

    @Autowired private UserRepository userRepo;
    @Autowired private JobRepository jobRepo;
    @Autowired private ApplicationRepository appRepo;
    @Autowired private MatchingService matchingService;

    public Application apply(String email, Long jobId) {
        User user = userRepo.findByEmail(email).orElseThrow();
        Job job = jobRepo.findById(jobId).orElseThrow();

        if (appRepo.existsByUserIdAndJobId(user.getId(), jobId)) {
            throw new RuntimeException("Already applied");
        }

        int score = matchingService.calculateMatchScore(user, job);

        Application app = new Application();
        app.setUserId(user.getId());
        app.setJobId(jobId);
        app.setMatchScore(score);
        app.setStatus(ApplicationStatus.APPLIED);

        return appRepo.save(app);
    }
}