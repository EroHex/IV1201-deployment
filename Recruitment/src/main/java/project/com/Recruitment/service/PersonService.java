package project.com.Recruitment.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import project.com.Recruitment.model.Person;
import project.com.Recruitment.repository.PersonRepository;
import java.util.*;

@Service
public class PersonService {
    
    @Autowired
    private PersonRepository personRepository;

    // @Autowired
    // private BCryptPasswordEncoder passwordEncoder; 


    /**
     * Method to check whether user exist, used in login
     * @param username the username provided by the user
     * @param password the password provided by the user
     * @return True if both username and password match a person in database
     */
    public boolean validateUser(String username, String password) {
        Optional<Person> person = personRepository.findByUsername(username);

        if (person.isPresent()) {
            String storedPassword = person.get().getPassword();
            System.out.println("Stored password: " + storedPassword); //visas bara i cmd för debugging
            System.out.println("Entered password: " + password);

            if (storedPassword.equals(password)) {
                System.out.println("Password match for person: " + username);
                return true;
            } else {
                System.out.println("Incorrect password for person: " + username);
                return false;
            }
        } else {
            System.out.println("No person found with username: " + username);
            return false;
        }
    }

    /**
     * Method to register a new user
     * Checks if username is already in use, if so throw Exception
     * Otherwise add user to database
     * @param username the username provided by the user
     * @param password the password provided by the user
     * @return  saves the new user into person table in database
     * @todo    Add additional info to be added, e.g. name, surname
     * @todo    Password encryption
     */
    @Transactional
    public Person registerPerson(String username, String password) {
        if (personRepository.findByUsername(username).isPresent()) {
            throw new RuntimeException("Username already exists!");
        }
        // String hashedPassword = passwordEncoder.encode(password);
        Person newPerson = new Person(username, password);
        return personRepository.save(newPerson);
    }

    public List<Person> getApplications() {
        return personRepository.findByRoleId(2); // Only fetch persons with role_id = 2
    }
}
