package com.piotrszustak.quizApp.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.piotrszustak.quizApp.dtos.GameOptionsDto;
import com.piotrszustak.quizApp.dtos.QuestionsDto;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.List;

@Service
@Log
public class QuestionService {

    public List<QuestionsDto.QuestionDto> getAllQuestions(GameOptionsDto gameOptions) {
        try {
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
        } catch (ResourceAccessException | HttpClientErrorException e) {
            log.info("Online resource access failed");
            log.info("Loading questions from backup...");
        }

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            List<QuestionsDto.QuestionDto> allQuestionsFromBackup = objectMapper.readValue(new File("src/main/resources/backups/questions.json"), QuestionsDto.class).getQuestions();
            String chosenCategory = null;
            switch (gameOptions.getChosenCategoryId()) {
                case 9 -> chosenCategory = "General Knowledge";
                case 21 -> chosenCategory = "Sports";
                case 22 -> chosenCategory = "Geography";
                case 23 -> chosenCategory = "History";
            }
            String finalChosenCategory = chosenCategory;
            List<QuestionsDto.QuestionDto> filteredQuestionList = allQuestionsFromBackup.stream()
                    .filter(question -> question.getCategory().equals(finalChosenCategory)
                            && question.getDifficulty().equals(gameOptions.getChosenDifficulty().getLabel().toLowerCase()))
                    .limit(gameOptions.getChosenNumberOfQuestions())
                    .toList();
            log.info("Questions loaded from backup: " + filteredQuestionList);
            return filteredQuestionList;
        } catch (IOException e) {
            throw new RuntimeException("Loading questions from backup failed", e);
        }
    }
}
