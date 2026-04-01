package com.baalvionService.JobPortalApplication.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baalvionService.JobPortalApplication.entities.Application;
import com.baalvionService.JobPortalApplication.entities.User;
import com.baalvionService.JobPortalApplication.repositories.ApplicationRepository;
import com.baalvionService.JobPortalApplication.repositories.UserRepository;

@RestController
@RequestMapping("baalvion/api/company")
public class CompanyController {

	@Autowired
	private ApplicationRepository appRepo;
	@Autowired
	private UserRepository userRepo;

	@GetMapping("/jobs/{jobId}/applications")
	public List<Map<String, Object>> getApplications(@PathVariable Long jobId) {

		List<Application> apps = appRepo.findByJobId(jobId);

		return apps.stream().map(app -> {
			User user = userRepo.findById(app.getUserId()).orElseThrow();

			Map<String, Object> res = new HashMap<>();
			res.put("name", user.getUsername());
			res.put("skills", user.getSkills());
			res.put("experience", user.getExperience());
			res.put("matchScore", app.getMatchScore());
			res.put("status", app.getStatus()); 

			return res;
		}).sorted((a, b) -> (int) b.get("matchScore") - (int) a.get("matchScore")).toList();
	}
	
	
}
