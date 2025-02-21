package project.com.Recruitment.model;

import jakarta.persistence.*;

@Entity
@Table(name = "person") //ska vara samma som table i databasen
public class Person{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long person_id;

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

    @Column(nullable = false)
    private int role_id;


    public Person() {}

    public Person(String username, String password, String email, String name, String surname, String pnr, int role_id) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.pnr = pnr;
        this.role_id = role_id;
    }

    public Long getId() { return person_id; }
    public void setId(Long id) { this.person_id = id; }

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

    public int getRoleId() { return role_id; }
    public void setRoleId(int role_id) { this.role_id = role_id; }
}