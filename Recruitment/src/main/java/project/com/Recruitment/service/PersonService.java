package project.com.Recruitment.service;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.com.Recruitment.model.Person;
import project.com.Recruitment.repository.PersonRepository;
import java.util.*;
import project.com.Recruitment.dto.RegisterDTO;
import project.com.Recruitment.dto.LoginDTO;

import java.util.Optional;

@Service
public class PersonService{
    
    @Autowired
    private PersonRepository personRepository;

    public boolean validateUser(LoginDTO loginDTO) {
        Optional<Person> person = personRepository.findByUsername(loginDTO.getUsername());

        if (person.isPresent()) {
            String storedPassword = person.get().getPassword();
            System.out.println("Stored password: " + storedPassword); //visas bara i cmd för debugging
            System.out.println("Entered password: " + loginDTO.getPassword());

            if (storedPassword.equals(loginDTO.getPassword())) {
                System.out.println("Password match for person: " + loginDTO.getUsername());
                return true;
            } else {
                System.out.println("Incorrect password for person: " + loginDTO.getUsername());
                return false;
            }
        } else {
            System.out.println("No person found with username: " + loginDTO.getUsername());
            return false;
        }
    }

    public Person getPersonByUsername(String username) {
        Optional<Person> person = personRepository.findByUsername(username);
        return person.orElse(null);
    }
    

    @Transactional
    public Person registerPerson(RegisterDTO registerDTO) {
        if (personRepository.findByUsername(registerDTO.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists!");
        }
        if (personRepository.findByEmail(registerDTO.getEmail()).isPresent()) {
            throw new RuntimeException("That email aldready has an account associated with it!");
        }
        if (personRepository.findByPnr(registerDTO.getPnr()).isPresent()) {
            throw new RuntimeException("That personal number is already in use!");
        }
        Person newPerson = new Person(registerDTO.getUsername(), registerDTO.getPassword(), registerDTO.getEmail(), registerDTO.getName(), registerDTO.getSurname(), registerDTO.getPnr(), 1);
        return personRepository.save(newPerson);
    }

    /**
     * Method to retrieve all applications
     * 2L is for filtering role_id to only show applicants 
     * @return a list of persons with their applications
     */
    public List<Person> getAllApplications() {
        return personRepository.findByRoleId(2L); // Only fetch persons with role_id = 2
    }

    public Optional<Person> getPersonById(Long id){
        System.out.println(id);
        return personRepository.findByPersonId(id);
    }
}
