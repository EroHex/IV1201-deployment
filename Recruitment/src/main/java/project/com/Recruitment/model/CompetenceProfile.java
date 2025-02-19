package project.com.Recruitment.model;

import jakarta.persistence.*;

@Entity
@Table(name = "competence_profile") //ska vara samma som table i databasen
public class CompetenceProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "competence_profile_id")
    private Long competenceProfileId;

    @Column(name = "competence_id")
    private Long competenceId;

    @Column(name = "years_of_experience")
    private Long yearsOfExperience;

    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;

    /**
     * Get and Set methods for each property
     * @return  the corresponding property
     */
    public Long getCompetenceProfileId() { return competenceProfileId; }
    public void setCompetenceProfileId(Long competenceProfileId) { this.competenceProfileId = competenceProfileId; }

    public Long getCompetenceId() { return competenceId; }
    public void setCompetenceId(Long competenceId) { this.competenceId = competenceId; }

    public Long getYearsOfExperience() { return yearsOfExperience; }
    public void setYearsOfExperience(Long yearsOfExperience) { this.yearsOfExperience = yearsOfExperience; }

    public Person getPerson() { return person; }
    public void setPerson(Person person) { this.person = person; }
}
