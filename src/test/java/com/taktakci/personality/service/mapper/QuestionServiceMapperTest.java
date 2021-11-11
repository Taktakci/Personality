package com.taktakci.personality.service.mapper;

import com.taktakci.personality.repository.entity.QuestionCategory;
import com.taktakci.personality.repository.entity.QuestionType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

class QuestionServiceMapperTest {

    private QuestionServiceMapper questionServiceMapper;

    @BeforeEach
    void setUp() {
        questionServiceMapper = Mockito.mock(QuestionServiceMapper.class,
                Mockito.CALLS_REAL_METHODS);
    }

    @Test
    void mapQuestionCategoryToStringSuccessful() {

        QuestionCategory category = new QuestionCategory();
        category.setText("lifestyle");
        String result = questionServiceMapper.mapQuestionCategoryToString(category);

        Assertions.assertEquals("lifestyle", result);
    }

    @Test
    void mapQuestionTypeToStringSuccessful() {

        QuestionType type = new QuestionType();
        type.setText("single_choice");
        String result = questionServiceMapper.mapQuestionTypeToString(type);

        Assertions.assertEquals("single_choice", result);
    }

    @Test
    void mapCommaSeperatedValuesSuccessful() {

        List<String> valueList = questionServiceMapper.mapCommaSeperatedValues("one;two;three");

        Assertions.assertEquals(3, valueList.size());
        Assertions.assertEquals("two", valueList.get(1));
    }

}