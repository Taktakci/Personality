package com.taktakci.personality.service;

import com.taktakci.personality.repository.AnswerRepository;
import com.taktakci.personality.repository.entity.Answer;
import com.taktakci.personality.service.exceptions.BusinessException;
import com.taktakci.personality.service.mapper.AnswerServiceMapper;
import com.taktakci.personality.service.model.AnswerDto;
import com.taktakci.personality.service.model.QuestionDto;
import com.taktakci.personality.service.validator.AnswerServiceValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerService {

    private final QuestionService questionService;
    private final AnswerRepository answerRepository;
    private final AnswerServiceMapper answerServiceMapper;
    private final AnswerServiceValidator answerServiceValidator;

    @Autowired
    public AnswerService(QuestionService questionService,
                         AnswerRepository answerRepository,
                         AnswerServiceMapper answerServiceMapper,
                         AnswerServiceValidator answerServiceValidator) {
        this.questionService = questionService;
        this.answerRepository = answerRepository;
        this.answerServiceMapper = answerServiceMapper;
        this.answerServiceValidator = answerServiceValidator;
    }

    public AnswerDto answerQuestion(AnswerDto answerDto) {

        QuestionDto questionDto = questionService.getQuestionById(answerDto.getQuestionId());
        answerServiceValidator.checkQuestionUnanswered(answerDto.getUserId(), answerDto.getQuestionId());
        answerServiceValidator.checkAnswer(answerDto, questionDto);
        updateConditionalQuestion(answerDto, questionDto);

        Answer answer = answerServiceMapper.toAnswer(answerDto);
        answerRepository.save(answer);
        return answerServiceMapper.toAnswerDto(answer);
    }

    private void updateConditionalQuestion(AnswerDto answerDto, QuestionDto questionDto) {

        // if the user gives the expected answer then we set the conditional question
        if (questionDto.getExpectedAnswer() != null &&
                questionDto.getExpectedAnswer().equals(answerDto.getAnswerText())) {
            answerDto.setConditionalQuestionId(questionDto.getConditionalQuestionId());
        }
    }

    public AnswerDto getUserAnswer(String userId, Integer questionId) {
        Answer answer = answerRepository.findByQuestionIdAndUserId(userId, questionId);
        if (answer == null) {
            throw new BusinessException("Answer not found for userId: " + userId +
                    " and questionId: " + questionId);
        }
        return answerServiceMapper.toAnswerDto(answer);
    }

    public List<AnswerDto> getUserAnswers(String userId) {
        List<Answer> answerList = answerRepository.getUserAnswers(userId);
        return answerServiceMapper.toAnswerDtoList(answerList);
    }
}
