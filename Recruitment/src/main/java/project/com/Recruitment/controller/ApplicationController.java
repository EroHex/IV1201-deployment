package project.com.Recruitment.controller;

import project.com.Recruitment.model.Person;
import project.com.Recruitment.service.PersonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/applicationInfo")
public class ApplicationController {

    @Autowired
    private PersonService personService;

    /**
     * Retrieve a single application based on the id clicked on by recruiter
     * @param id the personId of the clicked on application, should probably be changed
     * @param model model passed to spring for html showing
     * @return html page
     */
    @GetMapping
    public String showApplication(@RequestParam("id") Long id, Model model) {
        Person applicationInfo = personService.getPersonById(id).get();
        model.addAttribute("applicationInfo", applicationInfo);
        // model.asMap().forEach((key, value) -> System.out.println("Model Key: " + key + " -> " + value));

        return "applicationInfo";
    }
}