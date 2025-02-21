package project.com.Recruitment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import project.com.Recruitment.service.PersonService;
import project.com.Recruitment.dto.RegisterDTO;
import project.com.Recruitment.dto.LoginDTO;
import jakarta.validation.*;
import org.springframework.validation.*;

import project.com.Recruitment.model.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import jakarta.servlet.http.HttpSession;
@Controller
// @RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    // login via login.html filen 
    
    @GetMapping("/")
    public String homePage(Model model, HttpSession session) {
        if (session.getAttribute("loggedInUser") == null) {
            return "redirect:/login"; // hittar filen i src/main/resources/templates/login.html
        }
        return "index"; // hittar filen i src/main/resources/templates/index.html
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login"; // hittar filen i src/main/resources/templates/login.html
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("registerDTO", new RegisterDTO());
        return "register"; // hittar filen i src/main/resources/templates/register.html
    }

    @PostMapping("/login")
    public String login(@Valid LoginDTO loginDTO, BindingResult bindingResult, HttpSession session, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("error", "Validation failed: " + bindingResult.getAllErrors());
            return "login";
        }
        boolean validUser = personService.validateUser(loginDTO); //skicka till service för databas hantering
        if (validUser) {
            Person person = personService.getPersonByUsername(loginDTO.getUsername());
            session.setAttribute("loggedInUser", person);
            System.out.println("User logged in: " + person);
            return "redirect:/"; // Redirect to home page after successful login
        } else {
            model.addAttribute("error", "Invalid username or password. Please try again.");
            return "login"; // Return to login page with error message
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
            return "redirect:/login";
        } catch (RuntimeException e) {
            return "register";      // add error handling
        }
    }
}


