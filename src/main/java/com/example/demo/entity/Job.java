package com.example.demo.entity;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;

@Document
public class Job {
	@Id
	private String id;
	
	@NotBlank(message = "Company name is required")
	@Size(min = 2, max = 100, message = "Company name must be between 2 and 100 characters")
	private String companyName;
	
	@NotBlank(message = "Job title is required")
	@Size(min = 3, max = 50, message = "Job title must be between 3 and 50 characters")
	private String jobTitle;
	
	@NotBlank(message = "Skills are required")
	@Size(min = 2, max = 200, message = "Skills length should be 2 and 200 characters")
	private String skills;
	
	@Min(value = 0, message = "Experience cannot be negative")
	@Max(value = 100, message = "Experience is too high")
	private Integer experience;
	
	@NotBlank(message = "Location is required")
	@Size(min = 2, max = 50, message = "Location must be between 2 and 50 characters")
	private String location;
	
	@NotNull
	@Min(0)
	private Long salary;
	
	@NotBlank(message = "Timing is required")
	private String timing;
	
	
	public Job() {
	}
		public Job(String id, String companyName, String jobTitle, String skills, Integer experience, String location, Long salary, String timing) {
			this.id = id;
			this.companyName =companyName;
			this.jobTitle = jobTitle;
			this.location = location;
			this.salary = salary;
			this.timing = timing;
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getCompanyName() {
			return companyName;
		}
		public void setCompanyName(String companyName) {
			this.companyName = companyName;
		}
		public String getJobTitle() {
			return jobTitle;
		}
		public void setJobTitle(String jobTitle) {
			this.jobTitle = jobTitle;
		}
		public String getSkills() {
			return skills;
		}
		public void setSkills(String skills) {
			this.skills = skills;
		}
		public Integer getExperience() {
			return experience;
		}
		public void setExperience(Integer experience) {
			this.experience = experience;
		}
		public String getLocation() {
			return location;
		}
		public void setLocation(String location) {
			this.location = location;
		}
		public Long getSalary() {
			return salary;
		}
		public void setSalary(Long salary) {
			this.salary = salary;
		}
		public String getTiming() {
			return timing;
		}
		public void setTiming(String timing) {
			this.timing = timing;
		}
	}
	
