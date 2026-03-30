package com.baalvionService.JobPortalApplication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.baalvionService.JobPortalApplication.entities.Job;

public interface JobRepository extends JpaRepository<Job, Long> {

}
