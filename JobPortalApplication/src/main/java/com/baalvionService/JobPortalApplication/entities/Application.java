package com.baalvionService.JobPortalApplication.entities;

import com.baalvionService.JobPortalApplication.enums.ApplicationStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "applications",
       uniqueConstraints = {
           @UniqueConstraint(columnNames = {"userId", "jobId"}) // prevent duplicate apply
       },
       indexes = {
           @Index(name = "idx_job_id", columnList = "jobId"),
           @Index(name = "idx_user_id", columnList = "userId")
       })
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;

    // Validation
    @NotNull(message = "User ID is required")
    private Long userId;

    @NotNull(message = "Job ID is required")
    private Long jobId;

    // Match score must be between 0–100
    @Min(value = 0, message = "Match score cannot be less than 0")
    @Max(value = 100, message = "Match score cannot be more than 100")
    private int matchScore;

    // Enum validation
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Status is required")
    private ApplicationStatus status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getJobId() {
		return jobId;
	}

	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}

	public int getMatchScore() {
		return matchScore;
	}

	public void setMatchScore(int matchScore) {
		this.matchScore = matchScore;
	}

	public ApplicationStatus getStatus() {
		return status;
	}

	public void setStatus(ApplicationStatus status) {
		this.status = status;
	}

	public Application(Long id, @NotNull(message = "User ID is required") Long userId,
			@NotNull(message = "Job ID is required") Long jobId,
			@Min(value = 0, message = "Match score cannot be less than 0") @Max(value = 100, message = "Match score cannot be more than 100") int matchScore,
			@NotNull(message = "Status is required") ApplicationStatus status) {
		super();
		this.id = id;
		this.userId = userId;
		this.jobId = jobId;
		this.matchScore = matchScore;
		this.status = status;
	}

	public Application() {
		super();
	}

	@Override
	public String toString() {
		return "Application [id=" + id + ", userId=" + userId + ", jobId=" + jobId + ", matchScore=" + matchScore
				+ ", status=" + status + "]";
	}

    
}