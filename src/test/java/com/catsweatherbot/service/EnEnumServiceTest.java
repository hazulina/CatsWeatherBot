package com.catsweatherbot.service;

import com.catsweatherbot.dictionary.response.EnAnswersEnum;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@SpringBootTest
@RunWith(SpringRunner.class)
class EnEnumServiceTest {

    @DisplayName("should return correct String - command answer from Enum")
    @ParameterizedTest(name = "{displayName} [{index}] {0}")
    @MethodSource("givenAnswersForEnumArguments")
    void chooseRightEnumAnswerForEnEnum(String description, String expectedAnswer, String givenCommandName) {
        //given
        //when
        String actual = EnAnswersEnum.getEnumByCommandName(givenCommandName)
                .chooseRightEnumAnswer(givenCommandName);

        //then
        assertThat(actual).as(description)
                .isNotNull()
                .isNotBlank()
                .isEqualTo(expectedAnswer);
    }

    static Stream<Arguments> givenAnswersForEnumArguments() {
        return Stream.of(
                arguments("start answer", "Hi, I'm Cats & Weather Bot!", "/start"),
                arguments("choose language answer", "What language should be set?", "/chooselanguage"),
                arguments("favourite city answer", "Send me city's name which weather you'd like to get every day", "/favouritecity"),
                arguments("wrong input answer", "Wrong Input! Only letters are allowed.", "wrongInput")
        );
    }

    @DisplayName("should return correct String - command error message from Enum")
    @ParameterizedTest(name = "{displayName} [{index}] {0}")
    @MethodSource("givenErrorMessageForEnumArguments")
    void chooseRightEnumErrorMessageForEnEnum(String description, String expectedErrorMessage, String givenCommandName) {
        //given
        //when
        String actual = EnAnswersEnum.getEnumByCommandName(givenCommandName)
                .chooseRightEnumErrorMessage(givenCommandName);

        //then
        assertThat(actual).as(description)
                .isNotNull()
                .isNotBlank()
                .isEqualTo(expectedErrorMessage);
    }

    static Stream<Arguments> givenErrorMessageForEnumArguments() {
        return Stream.of(
                arguments("start error message", "You've already started me!", "/start"),
                arguments("choose language error message", "Something suspicious is happen...", "/chooselanguage"),
                arguments("favourite city error message", "City not found! Maybe it is too small. Choose another one, please", "/favouritecity"),
                arguments("wrong input error message", "Extremely wrong input)", "wrongInput")
        );
    }

    @DisplayName("should return correct String - command backend answer from Enum")
    @ParameterizedTest(name = "{displayName} [{index}] {0}")
    @MethodSource("givenAnswersFromBackForEnumArguments")
    void chooseRightAnswerFromBackForEnEnum(String description, String expectedAnswerFromBack, String givenCommandName) {
        //given
        //when
        String actual = EnAnswersEnum.getEnumByCommandName(givenCommandName)
                .chooseRightAnswerFromBack(givenCommandName);

        //then
        assertThat(actual).as(description)
                .isNotNull()
                .isNotBlank()
                .isEqualTo(expectedAnswerFromBack);
    }

    static Stream<Arguments> givenAnswersFromBackForEnumArguments() {
        return Stream.of(
                arguments("start answer from back", "Nice to meet you. What can I do for you?", "/start"),
                arguments("choose language answer from back", "Language is set to English", "/chooselanguage"),
                arguments("favourite city answer from back", "Favourite city is set", "/favouritecity"),
                arguments("wrong input answer from back", "Wrong input! City not found. Try again, please", "wrongInput")
        );
    }
}