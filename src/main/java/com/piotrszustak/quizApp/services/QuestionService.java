package com.piotrszustak.quizApp.services;

import com.piotrszustak.quizApp.dtos.GameOptionsDto;
import com.piotrszustak.quizApp.dtos.QuestionsDto;
import jakarta.servlet.http.HttpSession;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Service
@Log
public class QuestionService {

    public List<QuestionsDto.QuestionDto> getAllQuestions(HttpSession session) {
        GameOptionsDto gameOptions = (GameOptionsDto) session.getAttribute("gameOptions");
        URI uri = UriComponentsBuilder
                .fromUriString("https://opentdb.com/api.php")
                .queryParam("amount", gameOptions.getChosenNumberOfQuestions())
                .queryParam("category", gameOptions.getChosenCategoryId())
                .queryParam("difficulty", gameOptions.getChosenDifficulty().getLabel().toLowerCase())
                .build()
                .toUri();
        RestTemplate restTemplate = new RestTemplate();
        QuestionsDto result = restTemplate.getForObject(uri, QuestionsDto.class);
        log.info("Questions obtained from " + uri + ": " + result.getQuestions());
        return result.getQuestions();
    }
}
