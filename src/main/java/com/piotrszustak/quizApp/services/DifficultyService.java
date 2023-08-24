package com.piotrszustak.quizApp.services;

import com.piotrszustak.quizApp.enums.Difficulty;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Log
public class DifficultyService {

    public List<Difficulty> getDifficultyLevels() {
        log.info("Generating a list of difficulty levels...");
        List<Difficulty> difficulties = new ArrayList<>();
        difficulties.addAll(Arrays.asList(Difficulty.values()));
        return difficulties;
    }
}
