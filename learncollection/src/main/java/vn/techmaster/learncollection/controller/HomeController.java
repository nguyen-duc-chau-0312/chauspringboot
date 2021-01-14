package vn.techmaster.learncollection.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import vn.techmaster.learncollection.model.Person;
import vn.techmaster.learncollection.repository.PersonRepositoryInterface;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private PersonRepositoryInterface personRepositoryInterface;

    @GetMapping("/")
    public String getAllPerson(Model model){
        List<Person> listPerson = personRepositoryInterface.getAll();
        model.addAttribute("people", listPerson);
        return "people";
    }
    @GetMapping(value = "/sortPeopleByFullName")
    public String getSortPeopleByFullName(Model model){
        List<Person> listSortPeople = personRepositoryInterface.sortPeopleByFullNameReversed();
        model.addAttribute("sortted", listSortPeople);
        return "home";
    }
}
