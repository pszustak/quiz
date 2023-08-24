package com.piotrszustak.quizApp.services;

import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Log
public class NumberService {

    public List<Integer> getNumbers() {
        log.info("Generating a list of question numbers...");
        List<Integer> numbers = new ArrayList<>();
        for (Integer i = 1; i <= 20; i++) {
            numbers.add(i);
        }
        return numbers;
    }
}
