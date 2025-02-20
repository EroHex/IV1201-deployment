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
    public String login(@Valid LoginDTO loginDTO, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "login";
        }
        boolean validUser = personService.validateUser(loginDTO); //skicka till service för databas hantering
        if (validUser) {
            return "redirect:/";
        } else {
            return "login";     //add exception handling
        }
    }
    @GetMapping("/login")
    public String showLoginPage(Model model) {
        model.addAttribute("loginDTO", new LoginDTO());
        return "login"; // Returns the register.html page
    }

    // register account via register.html filen
    @PostMapping("/register")
    public String register(@Valid RegisterDTO registerDTO, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "register";
        }

        try {
            personService.registerPerson(registerDTO); //skicka till service för databas hantering
            return "redirect:/login";
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
