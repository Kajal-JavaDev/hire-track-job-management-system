package com.example.demo.repository;

import com.example.demo.entity.Job;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;



public interface JobRepository extends MongoRepository<Job,String>{
	
	
	
	Page<Job> findByJobTitleContainingIgnoreCase(String jobTitle, Pageable pageable);
	Page<Job> findByLocationContainingIgnoreCase(String location, Pageable pageable);
	
	Page<Job> findByJobTitleContainingIgnoreCaseAndLocationIgnoreCase(String jobTitle, String location, Pageable pageable);

}
