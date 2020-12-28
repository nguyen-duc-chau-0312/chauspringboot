package com.example.topcarhomework.controller;

import com.example.topcarhomework.model.TopCar;
import com.example.topcarhomework.repository.TopCarDao;
import com.example.topcarhomework.request.SearchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/topcar")
public class TopCarController {

    @Autowired
    private TopCarDao topCarDao;

    @GetMapping
    public String listAll(Model model) {
        model.addAttribute("topcars", topCarDao.getAll());
        return "alltopcars";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("topcar", new TopCar());
        return "form";
    }

    @GetMapping(value = "/{id}")
    public String getByID(@PathVariable("id") int id, Model model) {
        Optional<TopCar> topCar = topCarDao.get(id);
        if (topCar.isPresent()) {
            model.addAttribute("topcar", topCar.get());
        }
        return "topcar";
    }

    @PostMapping("/save")
    public String save(TopCar topCar, BindingResult result, RedirectAttributes redirect) {
        if (result.hasErrors()) {
            return "form";
        }
        if (topCar.getId() > 0) { //Nếu có trường id có nghĩa là đây là edit form
            topCarDao.update(topCar);
        } else { //Nếu id ==0 có nghĩa book lần đầu được add
            topCarDao.add(topCar);
        }

        return "redirect:/topcar";
    }

    @GetMapping(value = "/delete/{id}")
    public String deleteByID(@PathVariable("id") int id) {
        topCarDao.deleteById(id);
        return "redirect:/topcar";
    }

    @GetMapping(value = "/edit/{id}")
    public String editBookId(@PathVariable("id") int id, Model model) {
        Optional<TopCar> book = topCarDao.get(id);
        if (book.isPresent()) {
            model.addAttribute("topcar", book.get());
        }
        return "form";
    }

    @GetMapping("/search") //Hiển thị form tìm kiếm
    public String searchForm(Model model) {
        model.addAttribute("searchrequest", new SearchRequest());
        return "search";
    }


    @PostMapping("/search")
    public String searchByKeyword(@ModelAttribute SearchRequest request, BindingResult bindingResult, Model model) {
        if (!bindingResult.hasFieldErrors()) {
            model.addAttribute("topcars", topCarDao.searchByKeyword(request.getKeyword()));
        }
        return "alltopcars";
    }

}
