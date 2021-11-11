package com.taktakci.personality.rest.mapper;

import com.taktakci.personality.rest.model.CategoryResponse;
import com.taktakci.personality.rest.model.QuestionIdListResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

class QuestionControllerMapperTest {

    private QuestionControllerMapper questionControllerMapper;

    @BeforeEach
    void setUp() {
        questionControllerMapper = Mockito.mock(QuestionControllerMapper.class,
                Mockito.CALLS_REAL_METHODS);
    }

    @Test
    void toCategoryResponseSuccessful() {

        List<String> categoryList = Arrays.asList("hard_fact", "introversion", "lifestyle");
        CategoryResponse response = questionControllerMapper.toCategoryResponse(categoryList);

        Assertions.assertEquals(categoryList, response.getCategoryList());
    }

    @Test
    void toQuestionIdListResponseSuccessful() {

        List<Integer> idList = Arrays.asList(2, 5, 7);
        QuestionIdListResponse response = questionControllerMapper.toQuestionIdListResponse(idList);

        Assertions.assertEquals(idList, response.getIdList());
    }
}