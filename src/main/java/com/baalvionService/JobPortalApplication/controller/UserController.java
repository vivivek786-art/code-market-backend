package com.baalvionService.JobPortalApplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baalvionService.JobPortalApplication.entities.User;
import com.baalvionService.JobPortalApplication.repositories.UserRepository;

@RestController
@RequestMapping("/baalvion/api/student")
public class UserController {

	@Autowired
	private UserRepository userRepo;

	// GET PROFILE
	@GetMapping("/profile")
	public User getProfile(Authentication auth) {
		return userRepo.findByEmail(auth.getName()).orElseThrow();
	}

	// UPDATE PROFILE
	@PutMapping("/profile")
	public User updateProfile(@RequestBody User updatedUser, Authentication auth) {

		User user = userRepo.findByEmail(auth.getName()).orElseThrow();

		user.setUsername(updatedUser.getUsername());
		user.setSkills(updatedUser.getSkills());
		user.setExperience(updatedUser.getExperience());

		return userRepo.save(user);
	}
}
