package com.piotrszustak.quizApp.controllers;

import com.piotrszustak.quizApp.dtos.GameOptionsDto;
import com.piotrszustak.quizApp.services.GameService;
import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Log
public class GameController {

    private GameOptionsDto gameOptions;

    private final GameService gameService;

    public GameController(final GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/")
    public String postGameOptionsForm(GameOptionsDto gameOptions) {
        this.gameOptions = gameOptions;
        log.info("Form data submitted: " + gameOptions);
        return "redirect:game";
    }

    @GetMapping("/game")
    public String game(Model model) {
        gameService.initGame(gameOptions);
        model.addAttribute("totalQuestionsNumber", gameService.getTotalQuestionsNumber());
        model.addAttribute("currentQuestionNumber", gameService.getCurrentQuestionNumber());
        model.addAttribute("currentQuestionContent", gameService.getCurrentQuestionContent());
        model.addAttribute("currentQuestionAnswers", gameService.getCurrentQuestionMixedUpAnswers());
        return "game";
    }
}
