package com.example.demo.controller;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Job;
import com.example.demo.service.JobService;

import jakarta.validation.Valid;

import com.example.demo.dto.JobResponseDTO;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;


@RestController
@SecurityRequirement(name = "bearerAuth")

public class JobController {
	private final JobService jobService;
	
	public JobController(JobService jobService) {
		this.jobService = jobService;
	}
	
	@PostMapping("/jobs")
	public JobResponseDTO createJob(@Valid @RequestBody Job job) {
		
		Job savedJob = jobService.saveJob(job);
		
		JobResponseDTO response = new JobResponseDTO();
		
		response.setCompanyName(savedJob.getCompanyName());
		response.setJobTitle(savedJob.getJobTitle());
		response.setSkills(savedJob.getSkills());
		response.setExperience(savedJob.getExperience());
		response.setLocation(savedJob.getLocation());
		response.setSalary(savedJob.getSalary());
		response.setTiming(savedJob.getTiming());
		
		return response;
		
	}
	
	@GetMapping("/jobs")
	public List<Job> getAllJobs(){
		return jobService.getAllJobs();
	}
	

	@GetMapping("/jobs/{id}")
	public Job getJobById(@PathVariable String id) {
		return jobService.getJobById(id);
	}
	
	

	@GetMapping("/job/page")
	public Page<Job> getJobs(@RequestParam int page, @RequestParam int size, @RequestParam String sortBy, @RequestParam String direction){
		Sort sort = direction.equalsIgnoreCase("desc")?Sort.by(sortBy).descending():Sort.by(sortBy).ascending();
		
		Pageable pageable = PageRequest.of(page,size,sort);
		
		return jobService.getJobs(pageable);
	}

	@GetMapping("/jobs/search-filter")
	public Page<Job> searchAndFilterJobs(@RequestParam(required = false) String jobTitle, @RequestParam(required = false) String location, @RequestParam int page, @RequestParam int size, @RequestParam String sortBy, @RequestParam String direction){
		Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
		
		Pageable pageable = PageRequest.of(page, size, sort);
		
		return jobService.searchAndFilterJobs(jobTitle, location, pageable);
	}
	
	@GetMapping("/admin/test")
	public String adminTest() {
		return "Welcome Admin";
	}

	@PutMapping("/jobs/{id}")
	public Job updateJob(@PathVariable String id, @RequestBody Job job) {
		return jobService.updateJob(id, job);
		
	}
	
	@DeleteMapping("/jobs/{id}")
	public void deleteJob(@PathVariable String id) {
		jobService.deleteJob(id);
	}
	

}
