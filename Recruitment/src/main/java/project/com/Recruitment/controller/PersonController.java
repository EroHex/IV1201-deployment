package project.com.Recruitment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.com.Recruitment.service.PersonService;
import project.com.Recruitment.dto.RegisterDTO;
import jakarta.validation.*;
import org.springframework.validation.*;

@RestController
// @RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    // login via login.html filen 
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password) {
        boolean validUser = personService.validateUser(username, password); //skicka till service för databas hantering
        if (validUser) {
            return ResponseEntity.ok("Login successful for " + username);
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }

    // register account via register.html filen
    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @ModelAttribute RegisterDTO registerDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body("Validation failed: " + bindingResult.getAllErrors());
        }

        try {
            personService.registerPerson(registerDTO); //skicka till service för databas hantering
            return ResponseEntity.ok("Person Registered Successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
