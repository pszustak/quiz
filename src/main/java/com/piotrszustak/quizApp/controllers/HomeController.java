package com.piotrszustak.quizApp.controllers;

import com.piotrszustak.quizApp.services.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final CategoryService categoryService;

    public HomeController(final CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/")
    public String getHome(Model model) {
        model.addAttribute("categories", categoryService.getAllCategories());
        return "index";
    }
}
