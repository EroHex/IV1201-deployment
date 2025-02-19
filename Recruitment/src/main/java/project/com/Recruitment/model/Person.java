package project.com.Recruitment.model;

import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name = "person") //ska vara samma som table i databasen
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id")
    private Long personId;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password; 

    // @Column(nullable = false)
    private String name;

    // @Column(nullable = false)
    private String surname;

    @Column(name = "role_id")
    private Integer roleId;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private List<Availability> availabilities;

    public Person() {}

    public Person(String username, String password) {
        this.username = username;
        this.password = password;
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

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSurname() { return surname; }
    public void setSurname(String surname) { this.surname = surname; }

    public int getRoleId() { return roleId; }
    public void setRoleId(int roleId) { this.roleId = roleId; }

    public List<Availability> getAvailabilities() { return availabilities; }
    public void setAvailabilities(List<Availability> availabilities) { this.availabilities = availabilities; }
}