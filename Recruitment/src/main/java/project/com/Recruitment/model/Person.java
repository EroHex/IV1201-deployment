package project.com.Recruitment.model;

import jakarta.persistence.*;

@Entity
@Table(name = "person") // måste matcha table namn
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long person_id;
    private String name;
    private String surname;

    public Long getPerson_id() {
        return person_id;
    }

    public void setPerson_id(Long person_id) {
        this.person_id = person_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
