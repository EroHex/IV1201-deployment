package project.com.Recruitment.model;

import jakarta.persistence.*;
import java.util.*;
// Figure out why it won't recognize the command. SpringData JPA is a dependency in Maven, so what's the issue?

//ATTENTION: According to a tutorial https://www.youtube.com/watch?v=31KTdfRH6nY&list=PL4o1CeTVjiKNn_RfqcvfXC5F8dsMlSRZ_&index=2&t=2615s&ab_channel=freeCodeCamp.org
//you can make a record instead of a class. Seems pretty neat! Research pros & cons. Based on the tutorial, it demands way fewer lines of code
//aha! But it is immutable, so once they're set you can't change them. Seems like not something we want since we want

@Entity
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id")
    private Long id;
    @Column(name = "name", nullable = false, unique = false)
    private String name;
    @Column(name = "surname", nullable = false, unique = false)
    private String surname;
    @Column(name = "pnr",nullable = false, unique = true)
    private String personalNumber; //Rename this to social security number? Make it an int? That way we could throw an exception if the user does not enter a number
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    @Column(name = "password", nullable = false, unique = true)
    private String password; //For security purposes, this should be hashed. Add "salt". See https://www.javatpoint.com/how-to-encrypt-password-in-java how to do it
    @Column(name = "role_id", nullable = false)
    private Role role; //The role is if this user is a job applicant or admin. Depending on which, they have different UX/functionalities on the website. See enum below
    @Column(name="username", nullable = true, unique = true)
    private String username;
    //Figure out why it won't recognize Application. The list's purpose is to gather all the applications that come in, listing out
    //the applicants' contact info and previous work experience. Should also state if the application has been handled or
    //EDIT: Switched to JobApplication --> gets recognized. The JobApplication is found in the model
    @OneToMany(mappedBy = "applicant", cascade = CascadeType.ALL)
    private List<JobApplication> applications;

    public Person() { }
    //A public constructor, now fixed. This one was put outside the class, making it not-declared.
    public Person(String name, String surname, String personalNumber, String email, String password, Role role, String username){
        this.name = name;
        this.surname = surname;
        this.personalNumber = personalNumber;
        this.email = email;
        this.password = password;
        this.role = role;
        this.username = username;


    }

    //Getters
    public String getName(){
        return name;
    }
    public void setName(String name) { this.name = name; }

    public String getSurname() { return  surname; }
    public void setSurname(String surname) { this.surname = surname; }

    public String getPersonalNumber(){
        return personalNumber;
    }
    public void setPersonalNumber(String personalNumber){ this.personalNumber = personalNumber; }

    public String getEmail(){
        return email;
    }
    public void setEmail(String email){ this.email = email; }

    public String getPassword(){
        return password;
    }
    public void setPassword(String password) { this.password = password; }

    public Long getId() {
        return id;
    }
    public void setId(Long id){ this.id = id; }

    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
}


//an enum gives you a set of values you can pick from. Since we want our app to be for either a jobapplicant or admin, the enum gives
//the values APPLICANT or ADMIN to the role
enum Role{
    APPLICANT, ADMIN
}
