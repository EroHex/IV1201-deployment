package project.com.Recruitment.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
// Figure out why it won't recognize the command. SpringData JPA is a dependency in Maven, so what's the issue?

//ATTENTION: According to a tutorial https://www.youtube.com/watch?v=31KTdfRH6nY&list=PL4o1CeTVjiKNn_RfqcvfXC5F8dsMlSRZ_&index=2&t=2615s&ab_channel=freeCodeCamp.org
//you can make a record instead of a class. Seems pretty neat! Research pros & cons. Based on the tutorial, it demands way fewer lines of code
//aha! But it is immutable, so once they're set you can't change them. Seems like not something we want since we want

@Entity
@Table(name = "person") //ska vara samma som table i databasen
public class Person{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id")
    private Long personId;

    @Column(nullable = false, unique = true)
    private String username;
    //Figure out why it won't recognize Application. The list's purpose is to gather all the applications that come in, listing out
    //the applicants' contact info and previous work experience. Should also state if the application has been handled or
    //EDIT: Switched to JobApplication --> gets recognized. The JobApplication is found in the model
    @OneToMany(mappedBy = "applicant", cascade = CascadeType.ALL)
    private List<JobApplication> applications;

    @Column(nullable = false)
    private String password; 

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @Column(nullable = false, unique = true)
    private String pnr;

    @Column(name = "role_id")
    private Long roleId;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Availability> availabilities;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<CompetenceProfile> competenceProfile;


    public Person() {}

    public Person(String username, String password, String email, String name, String surname, String pnr, Long roleId) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.pnr = pnr;
        this.roleId = roleId;
    }

    /**
     * Get and Set methods for each property
     * @return  the corresponding property
     */
    public Long getPersonId() { return personId; }
    public void setPersonId(Long personId) { this.personId = personId; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }


    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSurname() { return surname; }
    public void setSurname(String surname) { this.surname = surname; }

    public String getPnr() { return pnr; }
    public void setPnr(String pnr) { this.pnr = pnr; }

    public Long getRoleId() { return roleId; }
    public void setRoleId(Long roleId) { this.roleId = roleId; }

    public List<Availability> getAvailabilities() { return availabilities; }
    public void setAvailabilities(List<Availability> availabilities) { this.availabilities = availabilities; }

    public List<CompetenceProfile> getCompetenceProfile() { return competenceProfile; }
    public void setCompetenceProfile(List<CompetenceProfile> competenceProfile) { this.competenceProfile = competenceProfile; }
}
