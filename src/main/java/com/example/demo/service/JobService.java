package com.example.demo.service;
import org.springframework.stereotype.Service;

import com.example.demo.repository.JobRepository;
import com.example.demo.entity.Job;

import java.util.List;

import java.util.Optional;
import com.example.demo.exception.JobNotFoundException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


@Service
public class JobService {
	private final JobRepository jobRepository;
	
	public JobService(JobRepository jobRepository) {
		this.jobRepository = jobRepository;
	}
	public Job saveJob(Job job) {
		return jobRepository.save(job);
	}
	
	public List<Job> getAllJobs(){ 
		return jobRepository.findAll();
	}
	
	
	public Job getJobById(String id) {
		Optional<Job> job = jobRepository.findById(id);
		
		if(job.isPresent()){
			return job.get();
			
		}
		throw new  JobNotFoundException("Jon not found with id: "  +  id);
	}
	
	// UPDATE
	public Job updateJob(String id, Job job) {
		Optional<Job> existingJob = jobRepository.findById(id);
		
		if(existingJob.isPresent()) {
			
			Job existing = existingJob.get();
			
			existing.setCompanyName(job.getCompanyName());
			existing.setJobTitle(job.getJobTitle());
			existing.setSkills(job.getSkills());
			existing.setExperience(job.getExperience());
			existing.setLocation(job.getLocation());
			existing.setSalary(job.getSalary());
			existing.setTiming(job.getTiming());
			
			return jobRepository.save(existing);
		}
		
		// USE EXCEPTION
		throw new JobNotFoundException("Job not found with id: "  +  id);
	}
	
	//DELETE
	public void deleteJob(String id) {
		jobRepository.deleteById(id);
	}
	
	public Page<Job> getJobs(Pageable pageable){
		return jobRepository.findAll(pageable);
	}

	public Page<Job> searchAndFilterJobs(String jobTitle, String location, Pageable pageable){
		if(jobTitle != null && location != null) {
			return jobRepository.findByJobTitleContainingIgnoreCaseAndLocationIgnoreCase(jobTitle, location, pageable);
		}
		if(jobTitle != null) {
			return jobRepository.findByJobTitleContainingIgnoreCase(jobTitle, pageable);
		}
		if(location != null) {
			return jobRepository.findByLocationContainingIgnoreCase(location, pageable);
		}
		
		return jobRepository.findAll(pageable);
	}

}
