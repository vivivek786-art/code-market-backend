package com.baalvionService.JobPortalApplication.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "jobs")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ✅ FIX
    private Long id;

    // Title validation
    @NotBlank(message = "Title is required")
    @Size(max = 100, message = "Title too long")
    private String title;

    // Description validation
    @NotBlank(message = "Description is required")
    @Size(max = 1000, message = "Description too long")
    private String description;

    // Skills validation
    @NotBlank(message = "Required skills are required")
    @Pattern(regexp = "^[a-zA-Z, ]*$", message = "Invalid skills format")
    @Size(max = 255, message = "Skills too long")
    private String requiredSkills;

    // Experience validation
    @Min(value = 0, message = "Experience must be >= 0")
    @Max(value = 50, message = "Experience too large")
    private int minExperience;

    // Keep but don't trust from request
    @NotNull(message = "Company ID is required")
    private Long companyId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRequiredSkills() {
		return requiredSkills;
	}

	public void setRequiredSkills(String requiredSkills) {
		this.requiredSkills = requiredSkills;
	}

	public int getMinExperience() {
		return minExperience;
	}

	public void setMinExperience(int minExperience) {
		this.minExperience = minExperience;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public Job(Long id,
			@NotBlank(message = "Title is required") @Size(max = 100, message = "Title too long") String title,
			@NotBlank(message = "Description is required") @Size(max = 1000, message = "Description too long") String description,
			@NotBlank(message = "Required skills are required") @Pattern(regexp = "^[a-zA-Z, ]*$", message = "Invalid skills format") @Size(max = 255, message = "Skills too long") String requiredSkills,
			@Min(value = 0, message = "Experience must be >= 0") @Max(value = 50, message = "Experience too large") int minExperience,
			@NotNull(message = "Company ID is required") Long companyId) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.requiredSkills = requiredSkills;
		this.minExperience = minExperience;
		this.companyId = companyId;
	}

	public Job() {
		super();
	}

	@Override
	public String toString() {
		return "Job [id=" + id + ", title=" + title + ", description=" + description + ", requiredSkills="
				+ requiredSkills + ", minExperience=" + minExperience + ", companyId=" + companyId + "]";
	}

	
    
}