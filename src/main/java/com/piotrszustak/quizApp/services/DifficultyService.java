package com.piotrszustak.quizApp.services;

import com.piotrszustak.quizApp.enums.Difficulty;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class DifficultyService {

    public List<Difficulty> getDifficultyLevels() {
        List<Difficulty> difficulties = new ArrayList<>();
        difficulties.addAll(Arrays.asList(Difficulty.values()));
        return difficulties;
    }
}
