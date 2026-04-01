package com.baalvionService.JobPortalApplication.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.baalvionService.JobPortalApplication.entities.Job;
import com.baalvionService.JobPortalApplication.entities.User;

@Service
public class MatchingService {

    public int calculateMatchScore(User user, Job job) {
        List<String> userSkills = Arrays.asList(user.getSkills().split(","));
        List<String> jobSkills = Arrays.asList(job.getRequiredSkills().split(","));

        long matched = jobSkills.stream().filter(userSkills::contains).count();

        double skillScore = (double) matched / jobSkills.size();
        double expScore = user.getExperience() >= job.getMinExperience() ? 1 : 0;

        return (int)((skillScore * 70) + (expScore * 30));
    }
}