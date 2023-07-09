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
import java.util.stream.IntStream;

@SpringBootTest
@RunWith(SpringRunner.class)
class EnumServiceTest {

    @Test
    void chooseRightEnumAnswerForEnEnum() {
        List<String> testCommandList = createTestCommandList();
        List<String> testAnswerList = createTestEnAnswersList();
        IntStream.range(0, testCommandList.size()).forEach(i -> Assertions.assertEquals(
                EnAnswersEnum
                        .getEnumByCommandName(testCommandList.get(i))
                        .chooseRightEnumAnswer(testCommandList.get(i)),
                testAnswerList.get(i)
        ));
    }

    @Test
    void chooseRightEnumAnswerForRuEnum() {
        List<String> testCommandList = createTestCommandList();
        List<String> testAnswerList = createTestRuAnswersList();
        IntStream.range(0, testCommandList.size()).forEach(i -> Assertions.assertEquals(
                RuAnswersEnum
                        .getEnumByCommandName(testCommandList.get(i))
                        .chooseRightEnumAnswer(testCommandList.get(i)),
                testAnswerList.get(i)
        ));
    }

    @Test
    void chooseRightEnumErrorMessageForEnEnum() {
        List<String> testCommandList = createTestCommandList();
        List<String> testErrorMessageList = createTestEnErrorMessageList();
        IntStream.range(0, testCommandList.size()).forEach(i -> Assertions.assertEquals(
                EnAnswersEnum
                        .getEnumByCommandName(testCommandList.get(i))
                        .chooseRightEnumErrorMessage(testCommandList.get(i)),
                testErrorMessageList.get(i)
        ));
    }

    @Test
    void chooseRightEnumErrorMessageForRuEnum() {
        List<String> testCommandList = createTestCommandList();
        List<String> testErrorMessageList = createTestRuErrorMessageList();
        IntStream.range(0, testCommandList.size()).forEach(i -> Assertions.assertEquals(
                RuAnswersEnum
                        .getEnumByCommandName(testCommandList.get(i))
                        .chooseRightEnumErrorMessage(testCommandList.get(i)),
                testErrorMessageList.get(i)
        ));
    }

    @Test
    void chooseRightAnswerFromBackForEnEnum() {
        List<String> testCommandList = createTestCommandList();
        List<String> testAnswersFromBackList = createTestEnAnswersFromBackList();
        IntStream.range(0, testCommandList.size()).forEach(i -> Assertions.assertEquals(
                EnAnswersEnum
                        .getEnumByCommandName(testCommandList.get(i))
                        .chooseRightAnswerFromBack(testCommandList.get(i)),
                testAnswersFromBackList.get(i)
        ));
    }

    @Test
    void chooseRightAnswerFromBackForRuEnum() {
        List<String> testCommandList = createTestCommandList();
        List<String> testAnswersFromBackList = createTestRuAnswersFromBackList();
        IntStream.range(0, testCommandList.size()).forEach(i -> Assertions.assertEquals(
                RuAnswersEnum
                        .getEnumByCommandName(testCommandList.get(i))
                        .chooseRightAnswerFromBack(testCommandList.get(i)),
                testAnswersFromBackList.get(i)
        ));
    }


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

    private List<String> createTestEnErrorMessageList() {
        List<String> testEnErrorMessageList = new ArrayList<>();
        testEnErrorMessageList.add("You've already started me!");
        testEnErrorMessageList.add("Something suspicious is happen...");
        testEnErrorMessageList.add("City not found! Maybe it is too small. Choose another one, please");
        testEnErrorMessageList.add("");
        return testEnErrorMessageList;
    }

    private List<String> createTestRuErrorMessageList() {
        List<String> testRuErrorMessageList = new ArrayList<>();
        testRuErrorMessageList.add("Мы уже начали работу) Давай продолжим");
        testRuErrorMessageList.add("Случилось неожиданное! Ошибка в установке языка");
        testRuErrorMessageList.add("Город не найден! Возможно он слишком мал. Выберите другой город рядом с Вами, пожалуйста");
        testRuErrorMessageList.add("");
        return testRuErrorMessageList;
    }

    private List<String> createTestEnAnswersFromBackList() {
        List<String> testEnAnswersFromBackList = new ArrayList<>();
        testEnAnswersFromBackList.add("Nice to meet you. What can I do for you?");
        testEnAnswersFromBackList.add("Language is set to English");
        testEnAnswersFromBackList.add("Favourite city is set");
        testEnAnswersFromBackList.add("Wrong input! City not found. Try again, please");
        return testEnAnswersFromBackList;
    }

    private List<String> createTestRuAnswersFromBackList() {
        List<String> testRuAnswersFromBackList = new ArrayList<>();
        testRuAnswersFromBackList.add("Теперь мы знакомы, чем могу помочь?");
        testRuAnswersFromBackList.add("Установлен русский язык");
        testRuAnswersFromBackList.add("Любимый город установлен");
        testRuAnswersFromBackList.add("Ошибка ввода! Город не найден. Попробуйте снова.");
        return testRuAnswersFromBackList;
    }
}