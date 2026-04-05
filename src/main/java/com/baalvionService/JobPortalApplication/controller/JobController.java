package com.baalvionService.JobPortalApplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baalvionService.JobPortalApplication.entities.Application;
import com.baalvionService.JobPortalApplication.entities.Job;
import com.baalvionService.JobPortalApplication.repositories.JobRepository;
import com.baalvionService.JobPortalApplication.service.ApplicationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("baalvion/api")
public class JobController {

	@Autowired
	private JobRepository jobRepo;
	@Autowired
	private ApplicationService appService;

	@PostMapping("/company/jobs")
	public Job createJob(@Valid @RequestBody Job job) {
		return jobRepo.save(job);
	}

	@GetMapping("/student/jobs")
	public List<Job> getJobs() {
		return jobRepo.findAll();
	}

	@PostMapping("/student/jobs/{jobId}/apply")
	public Application apply(@PathVariable Long jobId, Authentication auth) {
		return appService.apply(auth.getName(), jobId);
	}

}
