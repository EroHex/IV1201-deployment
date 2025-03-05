package project.com.Recruitment.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;




/**
 * A model for the job application submitted by a job applicant.
 * This entity stores the information needed to create the applicaiton.
 */
@Entity
public class JobApplication {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long userId; //should this perhaps be public to connect with the other? //PS! Experimenting by changing from id to userId instead

    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;


    private int competenceId; //The applicant's previous job experience/competency
    private int availability; //Period the applicant can work. Thinking int for which weeks during summer, i.e w.26-30

    /**
     * A default constructor for the jobapplication. Needed for JPA/Hibernate
     */
    public JobApplication() {
    }

    /**
     * A constructor for the jobapplicaiton.
     * @param id The unique ID for the jobapplication
     * @param status The status for the applicaiton
     * @param competenceId The applicant's competency/previous job experience
     * @param availability The weeks the applicant can work
     */
    public JobApplication (Long userId, ApplicationStatus status, int competenceId, int availability){
        this.userId = userId;
        this.status = status;
        this.competenceId = competenceId;
        this.availability = availability;
    }

    /**
     * Gets the unique ID of the application
     * @return the job application id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * Sets the unique ID of the application
     * @param id The job applicaiton ID
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * Gets the currentstatus of the job application
     * @return the status
     */
    public ApplicationStatus getStatus() {
        return status;
    }

    /**
     * Sets the status of the job application
     * @param status The application status
     */
    public void setStatus(ApplicationStatus status) {
        this.status = status;
    }

    /**
     * Gets the user's previous job experience/competence
     * @return The competency as a string
     */
    public int getCompetenceId(){
        return competenceId;
    }

    /**
     * Sets the competency of the job applicant
     * @param competency The competence to set
     */
    public void setCompetency(int competenceId) {
        this.competenceId = competenceId;
    }

    /**
     * Gets the weeks the job applicant is available
     * @return The period of availability
     */
    public int getAvailability() {
        return availability;
    }

    /**
     * Sets the availability of the job applicant
     * @param availability The weeks to set that the applicant is available to work
     */
    public void setAvailability(int availability) {
        this.availability = availability;
    }
}

/**
 * enum for the application status, showing
 * if the application has been Accepted, Rejected, or unhandled by an admin
 */
enum ApplicationStatus{

    ACCEPTED, REJECTED, UNHANDLED,

}

/*enum ApplicationSubmissionStatus{
    //For the job applicant - överkurs! Inget krav
    SUBMITTED, CANCELLED, IN_PROGRESS
}*/
