package project.com.Recruitment.repository;

import org.springframework.stereotype.Repository;
import project.com.Recruitment.model.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;



@Repository //<-- Tells about this interface in the JobApplicationController
public interface JobApplicationRepository extends JpaRepository<JobApplication, Long>{
    //The handler should be able to filter based on the applicants experience
    JobApplication findByCompetency(String Competency);

    //They do it like this in the tutorial @58:45, is the issue that this is an interface and not a class? If so, why?
    private List<JobApplication> jobApplication= new ArrayList<>();

}
