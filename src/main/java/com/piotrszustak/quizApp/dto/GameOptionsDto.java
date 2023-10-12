package com.piotrszustak.quizApp.dto;

import com.piotrszustak.quizApp.enums.Difficulty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class GameOptionsDto {

    private int chosenCategoryId;
    private Difficulty chosenDifficulty;
    private int chosenNumberOfQuestions;
}
