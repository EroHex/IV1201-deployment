package project.com.Recruitment.model;

import jakarta.persistence.*;

@Entity
@Table(name = "availability") //ska vara samma som table i databasen
public class Availability {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "availability_id")
    private Long availabilityId;

    private String fromDate;
    private String toDate;

    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;

    /**
     * Get and Set methods for each property
     * @return  the corresponding property
     */
    public Long getAvailabilityId() { return availabilityId; }
    public void setAvailabilityId(Long availabilityId) { this.availabilityId = availabilityId; }

    public String getFromDate() { return fromDate; }
    public void setFromDate(String fromDate) { this.fromDate = fromDate; }

    public String getToDate() { return toDate; }
    public void setToDate(String toDate) { this.toDate = toDate; }

    public Person getPerson() { return person; }
    public void setPerson(Person person) { this.person = person; }
}
