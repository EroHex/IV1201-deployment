package project.com.Recruitment.model;

import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name = "person") //ska vara samma som table i databasen
public class Person{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id")
    private Long personId;

    @Column(nullable = false, unique = true)
    private String username;

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