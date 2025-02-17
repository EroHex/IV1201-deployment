package project.com.Recruitment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.com.Recruitment.service.PersonService;

@RestController
// @RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    // login via login.html filen
    @PostMapping("/login")
    public ResponseEntity<String> login(
            @RequestParam String username,
            @RequestParam String password) {
        boolean validUser = personService.validateUser(username, password); //skicka till service för databas hantering
        if (validUser) {
            return ResponseEntity.ok("Login successful for " + username);
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }

    // register account via register.html filen
    @PostMapping("/register")
    public ResponseEntity<String> register(
            @RequestParam String name,
            @RequestParam String surname,
            @RequestParam String personalNumber,
            @RequestParam String email,
            @RequestParam String username,
            @RequestParam String password) {
        try {
            personService.registerPerson(name, surname, personalNumber, email, username, password); //skicka till service för databas hantering
            return ResponseEntity.ok("User has been registered successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}