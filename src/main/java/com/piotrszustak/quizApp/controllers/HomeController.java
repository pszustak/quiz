package com.piotrszustak.quizApp.controllers;

import com.piotrszustak.quizApp.services.CategoryService;
import com.piotrszustak.quizApp.services.NumberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final CategoryService categoryService;

    private final NumberService numberService;

    public HomeController(final CategoryService categoryService, final NumberService numberService) {
        this.categoryService = categoryService;
        this.numberService = numberService;
    }

    @GetMapping("/")
    public String getHome(Model model) {
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("numbers", numberService.getNumbers());
        return "index";
    }
}
