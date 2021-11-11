package com.taktakci.personality.rest;

import com.taktakci.personality.rest.mapper.QuestionControllerMapper;
import com.taktakci.personality.rest.model.CategoryResponse;
import com.taktakci.personality.rest.model.QuestionIdListResponse;
import com.taktakci.personality.rest.model.QuestionResponse;
import com.taktakci.personality.service.QuestionService;
import com.taktakci.personality.service.model.QuestionDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class QuestionControllerTest {

    @InjectMocks
    private QuestionController questionController;

    @Mock
    private QuestionService questionService;

    @Mock
    private QuestionControllerMapper mapper;

    @Test
    void getQuestionByIdSuccessful() {

        //mock questionService and mapper
        QuestionDto questionDto = new QuestionDto();
        questionDto.setQuestionText("who are you?");
        when(questionService.getQuestionById(4))
                .thenReturn(questionDto);
        QuestionResponse questionResponse = new QuestionResponse();
        questionResponse.setQuestionText("who are you?");
        when(mapper.toQuestionResponse(questionDto))
                .thenReturn(questionResponse);

        ResponseEntity<QuestionResponse> questionById = questionController.getQuestionById(4);

        Assertions.assertEquals("who are you?", questionById.getBody().getQuestionText());
    }

    @Test
    void getQuestionIdListByCategoryIdSuccessful() {

        //mock questionService and mapper
        List<Integer> idList = Arrays.asList(3, 5, 9);
        when(questionService.getQuestionIdListByCategoryId("introversion"))
                .thenReturn(idList);
        QuestionIdListResponse questionIdListResponse = new QuestionIdListResponse();
        questionIdListResponse.setIdList(idList);
        when(mapper.toQuestionIdListResponse(idList))
                .thenReturn(questionIdListResponse);

        ResponseEntity<QuestionIdListResponse> questionIdList =
                questionController.getQuestionIdListByCategoryId("introversion");

        Assertions.assertEquals(idList, questionIdList.getBody().getIdList());
    }

    @Test
    void getQuestionCategoriesSuccessful() {

        //mock questionService and mapper
        List<String> categoryList = Arrays.asList("hard_fact", "lifestyle", "passion");
        when(questionService.getQuestionCategories())
                .thenReturn(categoryList);
        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setCategoryList(categoryList);
        when(mapper.toCategoryResponse(categoryList))
                .thenReturn(categoryResponse);

        ResponseEntity<CategoryResponse> questionCategories = questionController.getQuestionCategories();

        Assertions.assertEquals(categoryList, questionCategories.getBody().getCategoryList());
    }
}