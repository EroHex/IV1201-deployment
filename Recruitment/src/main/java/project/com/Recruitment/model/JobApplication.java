package project.com.Recruitment.model;

import jakarta.persistence.*;

@Entity
public class JobApplication {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id; //should this perhaps be public to connect with the other?

    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;

    private String competency; //Is competency even a word? This is for the would be drop-down menu of industries applicant has experience in
    private int availability; //Period the applicant can work. Thinking int for which weeks during summer, i.e w.26-30



    public JobApplication (Long id, ApplicationStatus status, String competency, int availability){
        this.id = id;
        this.status = status;
        this.competency = competency;
        this.availability = availability;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ApplicationStatus getStatus() {
        return status;
    }

    public void setStatus(ApplicationStatus status) {
        this.status = status;
    }

    public String getCompetency(){
        return competency;
    }

    public void setCompetency(String competency) {
        this.competency = competency;
    }

    public int getAvailability() {
        return availability;
    }

    public void setAvailability(int availability) {
        this.availability = availability;
    }
}

enum ApplicationStatus{
    //For the Admin handling the job application
    ACCEPTED, REJECTED, UNHANDLED,
    //For the job applicant
    SUBMITTED, CANCELLED, IN_PROGRESS
}
