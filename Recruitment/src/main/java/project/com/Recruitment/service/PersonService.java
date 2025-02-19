package project.com.Recruitment.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import project.com.Recruitment.model.Person;
import project.com.Recruitment.repository.PersonRepository;
import project.com.Recruitment.dto.RegisterDTO;

import java.util.Optional;

@Service
public class PersonService {
    
    @Autowired
    private PersonRepository personRepository;

    // @Autowired
    // private BCryptPasswordEncoder passwordEncoder; 

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

    @Transactional
    public Person registerPerson(RegisterDTO registerDTO) {
        if (personRepository.findByUsername(registerDTO.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists!");
        }
        // String hashedPassword = passwordEncoder.encode(password);
        Person newPerson = new Person(registerDTO.getUsername(), registerDTO.getPassword());
        return personRepository.save(newPerson);
    }
}
