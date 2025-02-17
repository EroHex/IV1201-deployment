package project.com.Recruitment.model;

import jakarta.persistence.*;

@Entity
// @Table(name = "person") //ska vara samma som table i databasen
public class ListApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long person_id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @Column(nullable = false)
    private String from_date; 

    @Column(nullable = false)
    private String to_date; 

    @Column(nullable = false)
    private Long[] competence_id;

    public ListApplication() {}

    public ListApplication(String name, String surname, String from_date, String to_date, Long[] competence_id) {
        this.name = name;
        this.surname = surname;
        this.from_date = from_date;
        this.to_date = to_date;
        this.competence_id = competence_id;
    }

    public Long getPerson_id() { return person_id; }
    public void setPerson_id(Long person_id) { this.person_id = person_id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSurname() { return surname; }
    public void setSurname(String surname) { this.surname = surname; }

    public String getFrom_date() { return from_date; }
    public void setFrom_date(String from_date) { this.from_date = from_date; }

    public String getTo_date() { return to_date; }
    public void setTo_date(String to_date) { this.to_date = to_date; }

    public Long[] getCompetence_id() { return competence_id; }
    public void setCompetence_id(Long[] competence_id) { this.competence_id = competence_id; }

}