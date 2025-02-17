package project.com.Recruitment.controller;

import org.springframework.web.bind.annotation.RestController;
import project.com.Recruitment.model.JobApplication;
import project.com.Recruitment.repository.JobApplicationRepository;

import java.util.List;


//Since it's in its own package, JobApplication can't be found unless we import
//See tutorial @59:00
@RestController
public class JobApplicationController {

    private final JobApplicationRepository jobApplicationRepository;

    public JobApplication(JobApplicationRepository jobApplicationRepository){
        this.jobApplicationRepository = jobApplicationRepository;
    }

    List<JobApplication> findAll(){
        return null;
    }
}
