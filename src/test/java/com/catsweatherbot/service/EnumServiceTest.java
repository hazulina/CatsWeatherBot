package com.catsweatherbot.service;

import com.catsweatherbot.dictionary.response.EnAnswersEnum;
import com.catsweatherbot.dictionary.response.RuAnswersEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
class EnumServiceTest {

    private List<String> createTestCommandList() {
        List<String> testCommandsList = new ArrayList<>();
        testCommandsList.add("/start");
        testCommandsList.add("/chooselanguage");
        testCommandsList.add("/favouritecity");
        testCommandsList.add("wrongInput");
        return testCommandsList;
    }

    private List<String> createTestEnAnswersList() {
        List<String> testEnAnswersList = new ArrayList<>();
        testEnAnswersList.add("Hi, I'm Cats & Weather Bot!");
        testEnAnswersList.add("What language should be set?");
        testEnAnswersList.add("Send me city's name which weather you'd like to get every day");
        testEnAnswersList.add("Wrong Input! Only letters are allowed.");
        return testEnAnswersList;
    }

    private List<String> createTestRuAnswersList() {
        List<String> testRuAnswersList = new ArrayList<>();
        testRuAnswersList.add("Привет, я Бот, что рассказывает о погоде и показывает котиков!");
        testRuAnswersList.add("Какой язык установить?");
        testRuAnswersList.add("Укажите название города для ежедневной рассылки погоды");
        testRuAnswersList.add("Ошибка ввода! Допускаются только буквы :)");
        return testRuAnswersList;
    }

    @Test
    void chooseRightEnumAnswerForEnEnum() {
        List<String> testCommandList = createTestCommandList();
        List<String> testAnswerList = createTestEnAnswersList();
        for (int i = 0; i < testCommandList.size(); i++) {
            Assertions.assertEquals(
                    EnAnswersEnum.getEnumByCommandName(testCommandList.get(i)).chooseRightEnumAnswer(testCommandList.get(i)),
                    testAnswerList.get(i)
            );
        }
    }

    @Test
    void chooseRightEnumAnswerForRuEnum() {
        List<String> testCommandList = createTestCommandList();
        List<String> testAnswerList = createTestRuAnswersList();
        for (int i = 0; i < testCommandList.size(); i++) {
            Assertions.assertEquals(
                    RuAnswersEnum.getEnumByCommandName(testCommandList.get(i)).chooseRightEnumAnswer(testCommandList.get(i)),
                    testAnswerList.get(i)
            );
        }
    }

    @Test
    void chooseRightEnumErrorMessage() {
    }

    @Test
    void chooseRightAnswerFromBack() {
    }
}