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

    static final String DEFAULT_PAGE_URL = "/";
    static final String LOGIN_PAGE_URL = "login";
    static final String REGISTER_PAGE_URL = "register";
    static final String LOGOUT_PAGE_URL = "logout";
    static final String PROFILE_PAGE_URL = "account";
    static final String MANAGEAPPLICATIONS_PAGE_URL = "manage-applications";
    static final String CREATEAPPLICATION_PAGE_URL = "create-application";

    @Autowired
    private PersonService personService;

    // login via login.html filen 
    private boolean isLoggedIn(HttpSession session) {
        return session.getAttribute("loggedInUser") != null;
    }

    private boolean isAdmin(HttpSession session) {
        Person person = (Person) session.getAttribute("loggedInUser");
        if (person.getRoleId() == 2) {
            return true;
        }
        return false;
    }

    @GetMapping(DEFAULT_PAGE_URL)
    public String homePage(Model model, HttpSession session) {
        if (!(isLoggedIn(session))) {
            return "redirect:" + DEFAULT_PAGE_URL + LOGIN_PAGE_URL;
        }
        return "redirect:" + DEFAULT_PAGE_URL + PROFILE_PAGE_URL;
    }

    @GetMapping(DEFAULT_PAGE_URL + PROFILE_PAGE_URL)
    public String profilePage(Model model, HttpSession session) {
        if (!(isLoggedIn(session))) {
            return "redirect:" + DEFAULT_PAGE_URL + LOGIN_PAGE_URL;
        }
        return PROFILE_PAGE_URL;
    }

    @GetMapping(DEFAULT_PAGE_URL + LOGIN_PAGE_URL)
    public String loginPage(Model model) {
        model.addAttribute("loginDTO", new LoginDTO());
        return LOGIN_PAGE_URL; // hittar filen i src/main/resources/templates/login.html
    }

    @GetMapping(DEFAULT_PAGE_URL + REGISTER_PAGE_URL)
    public String registerPage(Model model) {
        model.addAttribute("registerDTO", new RegisterDTO());
        return REGISTER_PAGE_URL; // hittar filen i src/main/resources/templates/register.html
    }

    @PostMapping(DEFAULT_PAGE_URL + LOGIN_PAGE_URL)
    public String login(@Valid LoginDTO loginDTO, BindingResult bindingResult, HttpSession session, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("error", "Validation failed: " + bindingResult.getAllErrors());
            return LOGIN_PAGE_URL;
        }
        boolean validUser = personService.validateUser(loginDTO); //skicka till service för databas hantering
        if (validUser) {
            Person person = personService.getPersonByUsername(loginDTO.getUsername());
            session.setAttribute("loggedInUser", person);
            System.out.println("User logged in: " + person);
            return "redirect:" + DEFAULT_PAGE_URL; // Redirect to home page after successful login
        } else {
            model.addAttribute("error", "Invalid username or password. Please try again.");
            return LOGIN_PAGE_URL; // Return to login page with error message
        }
    }

    @PostMapping(DEFAULT_PAGE_URL + LOGOUT_PAGE_URL)
    public String logout(HttpSession session) {
        session.invalidate(); // Invalidate the session to log out the user
        return "redirect:" + DEFAULT_PAGE_URL + LOGOUT_PAGE_URL; // Redirect to login page after logout
    }
    
    // register account via register.html filen
    @PostMapping(DEFAULT_PAGE_URL + REGISTER_PAGE_URL)
    public String register(@Valid RegisterDTO registerDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return REGISTER_PAGE_URL;
        }

        try {
            personService.registerPerson(registerDTO); //skicka till service för databas hantering
            return "redirect:" + DEFAULT_PAGE_URL + LOGIN_PAGE_URL; // Redirect to login page after successful registration
        } catch (RuntimeException e) {
            return REGISTER_PAGE_URL;      // add error handling
        }
    }

    @GetMapping(DEFAULT_PAGE_URL + MANAGEAPPLICATIONS_PAGE_URL)
    public String manageApplications(Model model, HttpSession session) {
        if (!(isLoggedIn(session))) {
            return "redirect:" + DEFAULT_PAGE_URL + LOGIN_PAGE_URL;
        }
        if (!(isAdmin(session))) {
            return "redirect:" + DEFAULT_PAGE_URL;
        }
        return MANAGEAPPLICATIONS_PAGE_URL;
    }

    @GetMapping(DEFAULT_PAGE_URL + CREATEAPPLICATION_PAGE_URL)
    public String createApplication(Model model, HttpSession session) {
        if (!(isLoggedIn(session))) {
            return "redirect:" + DEFAULT_PAGE_URL + LOGIN_PAGE_URL;
        }
        return CREATEAPPLICATION_PAGE_URL;
    }
}


