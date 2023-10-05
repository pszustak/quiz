package com.piotrszustak.quizApp.services;

import com.piotrszustak.quizApp.dtos.GameOptionsDto;
import com.piotrszustak.quizApp.dtos.QuestionsDto;
import com.piotrszustak.quizApp.dtos.UsersAnswerDto;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class GameService {

    private int currentQuestionIndex;
    @Getter
    private int score;

    private List<QuestionsDto.QuestionDto> questions;

    private final QuestionService questionService;

    public GameService(final QuestionService questionService) {
        this.questionService = questionService;
    }

    public void initGame(GameOptionsDto gameOptions) {
        this.currentQuestionIndex = 0;
        this.score = 0;
        this.questions = questionService.getAllQuestions(gameOptions);
    }

    public String getCurrentQuestionCategory() {
        return questions.get(0).getCategory();
    }

    public String getDifficulty() {
        return questions.get(0).getDifficulty().toLowerCase();
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

    public void checkAnswerCorrectness(UsersAnswerDto usersAnswer) {
        String correctAnswer = questions.get(currentQuestionIndex).getCorrectAnswer();
        if (usersAnswer.getAnswer().equals(correctAnswer)) {
            score += 1;
        }
    }

    public boolean isNextQuestion() {
        currentQuestionIndex++;
        return currentQuestionIndex < questions.size();
    }

    public int getScorePercentage() {
        return score * 100 / questions.size();
    }

    public String getScoreComment() {
        if (getScorePercentage() == 0) {
            return "No worries, everyone has to start somewhere. Don't give up!";
        } else if (getScorePercentage() < 25) {
            return "You should give it another try! Don't give up!";
        } else if (getScorePercentage() < 50) {
            return "Great start! You still have a lot to discover.";
        } else if (getScorePercentage() < 75) {
            return "Your knowledge is expanding. Don't stop improving!";
        } else if (getScorePercentage() <= 99) {
            return "Nice! Your score is impressive.";
        } else {
            return "Perfect! This is mastery of knowledge.";
        }
    }
}
