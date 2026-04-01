package com.baalvionService.JobPortalApplication.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.baalvionService.JobPortalApplication.entities.Application;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

	List<Application> findByJobId(Long jobId);

	boolean existsByUserIdAndJobId(Long id, Long jobId);

}
