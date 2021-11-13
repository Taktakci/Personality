package com.taktakci.personality.service;

import com.taktakci.personality.repository.QuestionRepository;
import com.taktakci.personality.repository.entity.Question;
import com.taktakci.personality.service.exceptions.BusinessException;
import com.taktakci.personality.service.mapper.QuestionServiceMapper;
import com.taktakci.personality.service.model.QuestionDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class QuestionServiceTest {

    @InjectMocks
    private QuestionService questionService;

    @Mock
    private QuestionRepository questionRepository;

    @Mock
    private QuestionServiceMapper questionServiceMapper;

    @Test
    void getQuestionByIdSuccessful() {

        //mock questionRepository
        Question question = new Question();
        question.setId(5);
        question.setQuestionText("what is this?");
        Optional<Question> optionalQuestion = Optional.of(question);
        when(questionRepository.findById(5))
                .thenReturn(optionalQuestion);

        //mock questionServiceMapper
        QuestionDto questionDto = new QuestionDto();
        questionDto.setId(5);
        questionDto.setQuestionText("what is this?");
        when(questionServiceMapper.toQuestionDto(question))
                .thenReturn(questionDto);

        QuestionDto resultDto = questionService.getQuestionById(5);

        Assertions.assertNotNull(resultDto);
        Assertions.assertEquals(5, resultDto.getId());
        Assertions.assertEquals("what is this?", resultDto.getQuestionText());
    }

    @Test
    void getQuestionThrowsWhenQuestionRepositoryReturnsEmptyOptional() {

        //mock questionRepository
        Optional<Question> optionalQuestion = Optional.empty();
        when(questionRepository.findById(5))
                .thenReturn(optionalQuestion);

        BusinessException businessException = Assertions.assertThrows(BusinessException.class,
                () -> questionService.getQuestionById(5));

        Assertions.assertEquals("Question Not Found for id 5", businessException.getMessage());
    }

    @Test
    void getQuestionCategoriesSuccessful() {

        //mock questionRepository
        List<String> mockList = Arrays.asList("hard_fact", "introversion");
        when(questionRepository.findCategoryList())
                .thenReturn(mockList);

        List<String> categoryList = questionService.getQuestionCategories();

        Assertions.assertNotNull(categoryList);
        Assertions.assertEquals(mockList, categoryList);
    }

    @Test
    void getQuestionIdListByCategoryIdSuccessful() {

        //mock questionRepository
        List<Integer> mockList = Arrays.asList(3, 4, 6);
        when(questionRepository.findByCategoryId("hard_fact"))
                .thenReturn(mockList);

        List<Integer> questionIdList = questionService.getQuestionIdListByCategoryId("hard_fact");

        Assertions.assertNotNull(questionIdList);
        Assertions.assertEquals(mockList, questionIdList);
    }

}