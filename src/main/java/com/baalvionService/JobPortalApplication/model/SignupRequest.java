package com.baalvionService.JobPortalApplication.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class SignupRequest {

	@NotBlank(message = "Username is required")
	private String username;

	@Email(message = "Invalid email format")
	@NotBlank(message = "Email is required")
	private String email;

	@Size(min = 6, message = "Password must be at least 6 characters")
	private String password;

	@NotBlank(message = "Role is required")
	private String role;
	private String skills;

	@Min(value = 0, message = "Experience cannot be negative")
	private int experience;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getSkills() {
		return skills;
	}

	public void setSkills(String skills) {
		this.skills = skills;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

}
