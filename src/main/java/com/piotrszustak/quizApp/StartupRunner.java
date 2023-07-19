package com.piotrszustak.quizApp;

import com.piotrszustak.quizApp.services.QuizDataService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class StartupRunner implements CommandLineRunner {

    private final QuizDataService quizDataService;

    public StartupRunner(final QuizDataService quizDataService) {
        this.quizDataService = quizDataService;
    }

    @Override
    public void run(String... args) throws Exception {
        quizDataService.getQuizCategories();
    }
}
