package project.com.Recruitment.controller;

import project.com.Recruitment.model.Person;
import project.com.Recruitment.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@Controller
@RequestMapping("/listApplications")
public class ListApplicationsController {

    @Autowired
    private PersonService personService;

    /**
     * Retrieve all applications and add them to the model
     * Person object with data other tables included
     * @param model
     * @return the listApplication.html file found in /templates 
     */
    @GetMapping
    public String showApplications(Model model) {
        List<Person> applications = personService.getAllApplications();
        model.addAttribute("applications", applications);
        return "listApplication"; // This corresponds to the HTML file in templates
    }
}
