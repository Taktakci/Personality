package com.taktakci.personality.service;

import com.taktakci.personality.repository.AnswerRepository;
import com.taktakci.personality.repository.entity.Answer;
import com.taktakci.personality.service.exceptions.BusinessException;
import com.taktakci.personality.service.mapper.AnswerServiceMapper;
import com.taktakci.personality.service.model.AnswerDto;
import com.taktakci.personality.service.model.QuestionDto;
import com.taktakci.personality.service.validator.AnswerServiceValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AnswerServiceTest {

    @InjectMocks
    private AnswerService answerService;

    @Mock
    private QuestionService questionService;

    @Mock
    private AnswerRepository answerRepository;

    @Mock
    private AnswerServiceMapper answerServiceMapper;

    @Mock
    private AnswerServiceValidator answerServiceValidator;

    @Test
    void answerQuestionSuccessful() {

        //mock questionService
        QuestionDto questionDto = new QuestionDto();
        when(questionService.getQuestionById(3))
                .thenReturn(questionDto);

        //mock answerServiceMapper
        AnswerDto answerDto = new AnswerDto();
        answerDto.setAnswerText("this is my answer.");
        answerDto.setQuestionId(3);
        Answer answer = new Answer();
        answer.setAnswerText("this is my answer.");
        answer.setQuestionId(3);
        when(answerServiceMapper.toAnswer(answerDto))
                .thenReturn(answer);
        when(answerServiceMapper.toAnswerDto(answer))
                .thenReturn(answerDto);

        AnswerDto responseDto = answerService.answerQuestion(answerDto);

        Assertions.assertEquals("this is my answer.", responseDto.getAnswerText());
    }

    @Test
    void  getUserAnswerSuccessful() {

        Answer answer = new Answer();
        when(answerRepository.findByQuestionIdAndUserId("usr1", 6))
                .thenReturn(answer);
        AnswerDto answerDto = new AnswerDto();
        answerDto.setUserId("usr1");
        when(answerServiceMapper.toAnswerDto(answer))
                .thenReturn(answerDto);

        AnswerDto responseDto = answerService.getUserAnswer("usr1", 6);

        Assertions.assertEquals("usr1", responseDto.getUserId());
    }

    @Test
    void  getUserAnswerThrowsExceptionWhenAnswerRepositoryReturnsNull() {

        when(answerRepository.findByQuestionIdAndUserId("usr1", 6))
                .thenReturn(null);

        BusinessException businessException = Assertions.assertThrows(BusinessException.class,
                () -> answerService.getUserAnswer("usr1", 6));

        Assertions.assertEquals("Answer not found for userId: usr1 and questionId: 6", businessException.getMessage());

    }
    @Test
    void  getUserAnswersSuccessful() {

        Answer answer = new Answer();
        answer.setUserId("usr1");
        List<Answer> answerList = Collections.singletonList(answer);
        when(answerRepository.getUserAnswers("usr1"))
                .thenReturn(answerList);
        AnswerDto answerDto = new AnswerDto();
        answerDto.setUserId("usr1");
        List<AnswerDto> answerDtoList = Collections.singletonList(answerDto);
        when(answerServiceMapper.toAnswerDtoList(answerList))
                .thenReturn(answerDtoList);

        List<AnswerDto> responseList = answerService.getUserAnswers("usr1");

        Assertions.assertEquals("usr1", responseList.get(0).getUserId());
    }

}