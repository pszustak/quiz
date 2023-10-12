package com.piotrszustak.quizApp.controller;

import com.piotrszustak.quizApp.dto.GameOptionsDto;
import com.piotrszustak.quizApp.service.CategoryService;
import com.piotrszustak.quizApp.service.DifficultyService;
import com.piotrszustak.quizApp.service.NumberService;
import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Log
public class HomeController {

    private final CategoryService categoryService;

    private final NumberService numberService;

    private final DifficultyService difficultyService;

    public HomeController(final CategoryService categoryService, final NumberService numberService, final DifficultyService difficultyService) {
        this.categoryService = categoryService;
        this.numberService = numberService;
        this.difficultyService = difficultyService;
    }

    @GetMapping("/")
    public String getHome(Model model) {
        model.addAttribute("gameOptions", new GameOptionsDto());
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("numbers", numberService.getNumbers());
        model.addAttribute("difficulties", difficultyService.getDifficultyLevels());
        return "index";
    }
}
