package project.com.Recruitment.model;

public class JobApplication {
    private Long id; //should this perhaps be public to connect with the other?
    private ApplicationStatus status; //The current status of the application, which can be Accepted, Rejected, or Unhandeled, as per application-description.pdf
    private String competency; //Is competency even a word? This is for the would be drop-down menu of industries applicant has experience in
    private int availability; //Period the applicant can work. Thinking int for which weeks during summer, i.e w.26-30


    //Making a constructor, just as in the User-class. Should the enum actually be here?
    public JobApplication (Long id, ApplicationStatus status, String competency, int availability){
        this.id = id;
        this.status = status;
        this.competency = competency;
        this.availability = availability;
    }
}

enum ApplicationStatus{
    ACCEPTED, REJECTED, UNHANDLED
}
