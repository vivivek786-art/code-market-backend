package com.baalvionService.JobPortalApplication.entities;

import com.baalvionService.JobPortalApplication.enums.UserRole;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Username validation
    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 50, message = "Username must be 3-50 characters")
    private String username;

    // Email validation
    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    private String email;

    // Password validation
    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;

    // Enum validation
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Role is required")
    private UserRole role;

    // Skills validation
    @Pattern(regexp = "^[a-zA-Z, ]*$", message = "Invalid skills format")
    @Size(max = 255, message = "Skills too long")
    private String skills;

    // Experience validation
    @Min(value = 0, message = "Experience cannot be negative")
    @Max(value = 50, message = "Experience too large")
    private int experience;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
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

	public User(Long id,
			@NotBlank(message = "Username is required") @Size(min = 3, max = 50, message = "Username must be 3-50 characters") String username,
			@Email(message = "Invalid email format") @NotBlank(message = "Email is required") String email,
			@NotBlank(message = "Password is required") @Size(min = 6, message = "Password must be at least 6 characters") String password,
			@NotNull(message = "Role is required") UserRole role,
			@Pattern(regexp = "^[a-zA-Z, ]*$", message = "Invalid skills format") @Size(max = 255, message = "Skills too long") String skills,
			@Min(value = 0, message = "Experience cannot be negative") @Max(value = 50, message = "Experience too large") int experience) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.role = role;
		this.skills = skills;
		this.experience = experience;
	}

	public User() {
		super();
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", email=" + email + ", password=" + password + ", role="
				+ role + ", skills=" + skills + ", experience=" + experience + "]";
	}

}