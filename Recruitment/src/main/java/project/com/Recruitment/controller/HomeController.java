package project.com.Recruitment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import project.com.Recruitment.model.Person;
import project.com.Recruitment.repository.PersonRepository;

@Controller
public class HomeController {

    @Autowired
    private PersonRepository personRepository;

    @GetMapping("/")
    public String homePage(Model model) {
        // letar efter person med id 2
        Person person = personRepository.findById(2L).get();
        model.addAttribute("person", person);
        return "index"; // hittar filen i src/main/resources/templates/index.html
    }
}
