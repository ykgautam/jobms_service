package com.yash.jobms.job;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class JobController {

    JobService jobService;

    public JobController(JobService jobService){
        this.jobService=jobService;
    }

    @GetMapping("/jobs")
    public ResponseEntity<List<Job>> findAll(){

        return ResponseEntity.ok(jobService.findAll());
    }

    @PostMapping("/jobs")
    public String createJob(@RequestBody Job job){
        jobService.createJob(job);
        return "Job added successfully";
    }

    @GetMapping("/jobs/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable Long id){
    Job job= jobService.getJobById(id);
    if(job!=null)
       return new ResponseEntity<>(job, HttpStatus.OK);
    else
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/jobs/{id}")
    public ResponseEntity<String> deleteJobById(@PathVariable Long id){
        Boolean isDeleted= jobService.deleteJobById(id);
        if(isDeleted)
            return new ResponseEntity<>("deleted", HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
