package project.com.Recruitment.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.context.support.BeanDefinitionDsl;
import org.springframework.stereotype.Service;
import project.com.Recruitment.model.Person;
import project.com.Recruitment.repository.PersonRepository;

import java.util.Optional;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    // @Autowired
    // private BCryptPasswordEncoder passwordEncoder;

    public boolean validateUser(String username, String password) {
        //TODO: Why is this one not working?
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

    @Transactional
    public Person registerPerson(String name, String surname, String personalNumber, String email, String username, String password) {
        if (personRepository.findByUsername(username).isPresent()) {
            //TODO: Why RunTimeException? Changed to IllegalArgumentException 2025-02-16
            throw new IllegalArgumentException("Username already exists!");
        }
        //If I write personRepo, IntelliJ autosuggests .exists and .existsById. Are these built in?
        if(personRepository.findByEmail(email).isPresent()){
            throw new IllegalArgumentException("Email already exists");
        }
        //String hashedPassword = passwordEncoder.encode(password);
        //TODO: Figure out how to set the role. Should be set to applicant
        validateInput(name, surname, personalNumber, email, username, password);
        Person newPerson = new Person(name, surname, personalNumber, email, password, role, username);
        return personRepository.save(newPerson);
    }

    private void validateInput(String name, String surname, String personalNumber, String email, String username, String password){
        if (name.isEmpty() || surname.isEmpty() || personalNumber.isEmpty() || email.isEmpty() || username.isEmpty() || password.isEmpty()){
            throw new IllegalArgumentException("Please fill in all fields!");
        }
        if(!email.contains("@")){
            throw new IllegalArgumentException("Please enter a valid email using @!");
        }
    }
}