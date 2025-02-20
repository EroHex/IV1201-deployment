package project.com.Recruitment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.com.Recruitment.service.PersonService;
import project.com.Recruitment.dto.RegisterDTO;
import project.com.Recruitment.dto.LoginDTO;
import jakarta.validation.*;
import org.springframework.validation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
// @RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    // login via login.html filen 
    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @ModelAttribute LoginDTO loginDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body("Validation failed: " + bindingResult.getAllErrors());
        }
        boolean validUser = personService.validateUser(loginDTO); //skicka till service för databas hantering
        if (validUser) {
            return ResponseEntity.ok("Login successful for " + loginDTO.getUsername());
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }

    // register account via register.html filen
    @PostMapping("/register")
    public String register(@Valid RegisterDTO registerDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "register";
        }

        try {
            personService.registerPerson(registerDTO); //skicka till service för databas hantering
            return "register";
        } catch (RuntimeException e) {
            return "register";
        }
    }

    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        model.addAttribute("registerDTO", new RegisterDTO());
        return "register"; // Returns the register.html page
    }
}
