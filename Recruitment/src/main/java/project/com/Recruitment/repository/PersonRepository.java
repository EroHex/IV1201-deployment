package project.com.Recruitment.repository;

import project.com.Recruitment.model.Person;
import org.springframework.data.jpa.repository.JpaRepository; //Read the documentation more closely, perhaps it says here why it only accepts Long

public interface PersonRepository extends JpaRepository<Person, Long>{ //This won't let me use int. Switching to Long. Could be beneficial so that id can be null, too. Turns out that's how JpaRepository works.
    //Since the user will probably identify themselves via their email. Also, if admin searches for applicant, probably easiest with email
    Person findByEmail(String email);
    Person findByUsername(String username);

}
