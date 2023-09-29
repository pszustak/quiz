package com.piotrszustak.quizApp.services;

import com.piotrszustak.quizApp.dtos.GameOptionsDto;
import com.piotrszustak.quizApp.dtos.QuestionsDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class GameService {

    private int currentQuestionIndex;

    private List<QuestionsDto.QuestionDto> questions;

    private final QuestionService questionService;

    public GameService(final QuestionService questionService) {
        this.questionService = questionService;
    }

    public void initGame(GameOptionsDto gameOptions) {
        this.currentQuestionIndex = 0;
        this.questions = questionService.getAllQuestions(gameOptions);
    }

    public int getTotalQuestionsNumber() {
        return questions.size();
    }

    public int getCurrentQuestionNumber() {
        return currentQuestionIndex + 1;
    }

    public String getCurrentQuestionContent() {
        return questions.get(currentQuestionIndex).getQuestion();
    }

    public List<String> getCurrentQuestionMixedUpAnswers() {
        List<String> mixedUpAnswers = new ArrayList<>();
        mixedUpAnswers.addAll(questions.get(currentQuestionIndex).getIncorrectAnswers());
        mixedUpAnswers.add(questions.get(currentQuestionIndex).getCorrectAnswer());
        Collections.shuffle(mixedUpAnswers);
        return mixedUpAnswers;
    }
}
