package com.piotrszustak.quizApp.controller;

import com.piotrszustak.quizApp.dto.GameOptionsDto;
import com.piotrszustak.quizApp.dto.UsersAnswerDto;
import com.piotrszustak.quizApp.service.GameService;
import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Log
public class GameController {

    private final GameService gameService;

    public GameController(final GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/")
    public String postGameOptionsForm(GameOptionsDto gameOptions) {
        log.info("Form data submitted: " + gameOptions);
        gameService.initGame(gameOptions);
        return "redirect:game";
    }

    @GetMapping("/game")
    public String game(Model model) {
        model.addAttribute("usersAnswer", new UsersAnswerDto());
        model.addAttribute("currentQuestionCategory", gameService.getCurrentQuestionCategory());
        model.addAttribute("totalQuestionsNumber", gameService.getTotalQuestionsNumber());
        model.addAttribute("currentQuestionNumber", gameService.getCurrentQuestionNumber());
        model.addAttribute("currentQuestionContent", gameService.getCurrentQuestionContent());
        model.addAttribute("currentQuestionAnswers", gameService.getCurrentQuestionMixedUpAnswers());
        return "game";
    }

    @PostMapping("/game")
    public String postUsersAnswer(UsersAnswerDto usersAnswer) {
        log.info("Form data submitted: " + usersAnswer);
        gameService.checkAnswerCorrectness(usersAnswer);
        if (gameService.isNextQuestion()) {
            return "redirect:game";
        } else {
            return "redirect:result";
        }
    }

    @GetMapping("/result")
    public String result(Model model) {
        model.addAttribute("difficulty", gameService.getDifficulty());
        model.addAttribute("category", gameService.getCurrentQuestionCategory().toLowerCase());
        model.addAttribute("score", gameService.getScore());
        model.addAttribute("scorePercentage", gameService.getScorePercentage());
        model.addAttribute("totalQuestionsNumber", gameService.getTotalQuestionsNumber());
        model.addAttribute("scoreComment", gameService.getScoreComment());
        return "result";
    }
}
