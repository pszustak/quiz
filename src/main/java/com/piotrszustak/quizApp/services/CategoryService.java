package com.piotrszustak.quizApp.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.piotrszustak.quizApp.dtos.CategoriesDto;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
@Log
public class CategoryService {

    public List<CategoriesDto.CategoryDto> getAllCategories() {
        try {
            RestTemplate restTemplate = new RestTemplate();
            CategoriesDto result = restTemplate.getForObject("https://opentdb.com/api_category.php", CategoriesDto.class);
            log.info("Online resource access succeeded");
            log.info("Quiz categories downloaded from https://opentdb.com/api_category.php: " + result.getCategories());
            return result.getCategories();
        } catch (ResourceAccessException | HttpClientErrorException e) {
            log.info("Online resource access failed");
            log.info("Loading categories from backup...");
        }

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            CategoriesDto result = objectMapper.readValue(new File("src/main/resources/backups/categories.json"), CategoriesDto.class);
            log.info("Quiz categories loaded from backup: " + result.getCategories());
            return result.getCategories();
        } catch (IOException e) {
            throw new RuntimeException("Loading categories from backup failed", e);
        }
    }
}
