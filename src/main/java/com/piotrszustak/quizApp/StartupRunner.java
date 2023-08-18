package com.piotrszustak.quizApp;

import com.piotrszustak.quizApp.services.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class StartupRunner implements CommandLineRunner {

    private final CategoryService categoryService;

    public StartupRunner(final CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public void run(String... args) throws Exception {
        categoryService.getAllCategories();
    }
}
