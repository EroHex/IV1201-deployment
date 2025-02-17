package project.com.Recruitment.model;

import jakarta.persistence.*;

@Entity
@Table(name = "person") //ska vara samma som table i databasen
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long person_id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password; 

    public Person() {}

    public Person(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Long getId() { return person_id; }
    public void setId(Long id) { this.person_id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}