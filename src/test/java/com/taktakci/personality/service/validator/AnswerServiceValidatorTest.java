package com.taktakci.personality.service.validator;

import com.taktakci.personality.repository.AnswerRepository;
import com.taktakci.personality.repository.entity.Answer;
import com.taktakci.personality.service.AnswerService;
import com.taktakci.personality.service.exceptions.BusinessException;
import com.taktakci.personality.service.model.AnswerDto;
import com.taktakci.personality.service.model.QuestionDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AnswerServiceValidatorTest {

    @InjectMocks
    private AnswerServiceValidator answerServiceValidator;

    @Mock
    private AnswerRepository answerRepository;

    @Test
    void checkQuestionUnansweredSuccessful() {

        when(answerRepository.findByQuestionIdAndUserId("usr1", 6))
                .thenReturn(null);

        answerServiceValidator.checkQuestionUnanswered("usr1", 6);
    }

    @Test
    void checkQuestionUnansweredThrowsExceptionWhenRepositoryReturnsAnObject() {

        Answer answer = new Answer();
        answer.setAnswerText("this is my answer");
        when(answerRepository.findByQuestionIdAndUserId("usr1", 6))
                .thenReturn(answer);

        BusinessException businessException = Assertions.assertThrows(BusinessException.class,
                () -> answerServiceValidator.checkQuestionUnanswered("usr1", 6));

        Assertions.assertEquals("User already answered this question, answer is: this is my answer", businessException.getMessage());
    }

    @Test
    void checkAnswerSuccessfulWhenSingleChoiceConditional() {

        AnswerDto answerDto = new AnswerDto();
        answerDto.setAnswerText("opt2");
        QuestionDto questionDto = new QuestionDto();
        questionDto.setQuestionType("single_choice_conditional");
        List<String> options = Arrays.asList("opt1", "opt2", "opt3");
        questionDto.setOptions(options);

        answerServiceValidator.checkAnswer(answerDto, questionDto);
    }

    @Test
    void checkAnswerThrowsExceptionWhenSingleChoiceConditional() {

        AnswerDto answerDto = new AnswerDto();
        answerDto.setAnswerText("opt4");
        QuestionDto questionDto = new QuestionDto();
        questionDto.setQuestionType("single_choice_conditional");
        List<String> options = Arrays.asList("opt1", "opt2", "opt3");
        questionDto.setOptions(options);

        BusinessException businessException = Assertions.assertThrows(BusinessException.class,
                () -> answerServiceValidator.checkAnswer(answerDto, questionDto));

        Assertions.assertEquals("Your answer should be one of those: [opt1, opt2, opt3]", businessException.getMessage());
    }

    @Test
    void checkAnswerSuccessfulWhenNumberRange() {

        AnswerDto answerDto = new AnswerDto();
        answerDto.setAnswerText("25");
        QuestionDto questionDto = new QuestionDto();
        questionDto.setQuestionType("number_range");
        List<String> options = Arrays.asList("20", "30");
        questionDto.setOptions(options);

        answerServiceValidator.checkAnswer(answerDto, questionDto);
    }

    @Test
    void checkAnswerThrowsExceptionWhenOutsideNumberRange() {

        AnswerDto answerDto = new AnswerDto();
        answerDto.setAnswerText("35");
        QuestionDto questionDto = new QuestionDto();
        questionDto.setQuestionType("number_range");
        List<String> options = Arrays.asList("20", "30");
        questionDto.setOptions(options);

        BusinessException businessException = Assertions.assertThrows(BusinessException.class,
                () -> answerServiceValidator.checkAnswer(answerDto, questionDto));

        Assertions.assertEquals("Your answer should be between 20 and 30", businessException.getMessage());
    }

    @Test
    void checkAnswerThrowsExceptionWhenNotNumeric() {

        AnswerDto answerDto = new AnswerDto();
        answerDto.setAnswerText("abc");
        QuestionDto questionDto = new QuestionDto();
        questionDto.setQuestionType("number_range");
        List<String> options = Arrays.asList("20", "30");
        questionDto.setOptions(options);

        BusinessException businessException = Assertions.assertThrows(BusinessException.class,
                () -> answerServiceValidator.checkAnswer(answerDto, questionDto));

        Assertions.assertEquals("Your answer should be a numeric value", businessException.getMessage());
    }}