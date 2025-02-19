package project.com.Recruitment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.com.Recruitment.service.PersonService;

/**
 * Controller for Person
 * Should be changed so as to not use static html files
 */

@RestController
public class PersonController {

    @Autowired
    private PersonService personService;

    /**
     * Handles login
     * @param username the username provided by the user
     * @param password the password provided by the user
     * @return message for if user was logged in or not
     */
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password) {
        boolean validUser = personService.validateUser(username, password); //skicka till service för databas hantering
        if (validUser) {
            return ResponseEntity.ok("Login successful for " + username);
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }

    /**
     * Handles registration
     * @param username the username provided by the user
     * @param password the password provided by the user
     * @return message for if user was registered in or not
     */
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestParam String username, @RequestParam String password) {
        try {
            personService.registerPerson(username, password); //skicka till service för databas hantering
            return ResponseEntity.ok("Person Registered Successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
