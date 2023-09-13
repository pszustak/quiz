package com.piotrszustak.quizApp.controllers;

import com.piotrszustak.quizApp.dtos.GameOptionsDto;
import com.piotrszustak.quizApp.services.QuestionService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Log
public class GameController {

    private final QuestionService questionService;

    public GameController(final QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping("/game")
    public String postGameOptionsForm(@ModelAttribute GameOptionsDto gameOptions, HttpSession session) {
        log.info("Form data submitted: " + gameOptions);
        session.setAttribute("gameOptions", gameOptions);
        questionService.getAllQuestions(session);
        return "game";
    }
}
