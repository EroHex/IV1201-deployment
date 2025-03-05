package project.com.Recruitment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import project.com.Recruitment.model.JobApplication;
import project.com.Recruitment.service.JobApplicationService;


/**
 * A controller for handling job applications. It is returning the views for either the job applicant or for the admin, depending on who's logged in.
 * It handles saving the job application to the database or retrieving them.
 */
@RestController
public class JobApplicationController {

    private final JobApplicationService jobApplicationService;

    /**
     * Constructor of JobApplicationController
     *
     * @param jobApplicationService allows for storing/retrieving job applications
     */
    @Autowired
    public JobApplicationController(JobApplicationService jobApplicationService){
        this.jobApplicationService = jobApplicationService;
    }

    /**
     *  A method that allows the Admin to see all jobapplications
     * @return a list of all the applications that have been submitted
     */
    @GetMapping("/admin/applications")
    public List<JobApplication> findAll(){
        return jobApplicationService.getAllApplications();
    }

    /**
     * Saves the user's submitted application.
     * @param jobApplication The application that the user submits
     * @return a ResponseEntity with confirmation message that the application has been submitted
     */
    @PostMapping("/appSubmitted")
    public ResponseEntity<String> receiveApplication(@RequestBody JobApplication jobApplication){
        jobApplicationService.save(jobApplication);
        return ResponseEntity.ok("Job application submitted successfully!");
    }

    /**
     * Retrieves a saved application from a specific user
     * @param userId the logged in user's unique ID
     * @return the user's application
     */
    @PostMapping("/application")
    public ResponseEntity<JobApplication> getApplication(@RequestBody Long userId){
        jobApplicationService.getApplicationByUser(userId);
        return null;
    }
}