package com.taktakci.personality.service.validator;

import com.taktakci.personality.repository.AnswerRepository;
import com.taktakci.personality.repository.entity.Answer;
import com.taktakci.personality.service.exceptions.BusinessException;
import com.taktakci.personality.service.model.AnswerDto;
import com.taktakci.personality.service.model.QuestionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnswerServiceValidator {

    private final AnswerRepository answerRepository;

    @Autowired
    public AnswerServiceValidator(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    public void checkQuestionUnanswered(String userId, Integer questionId) {

        // if the question is already answered by the user then we throw exception
        Answer answer = answerRepository.findByQuestionIdAndUserId(userId, questionId);
        if (answer != null) {
            throw new BusinessException("User already answered this question, answer is: " + answer.getAnswerText());
        }
    }

    public void checkAnswer(AnswerDto answerDto, QuestionDto questionDto) {

        // if the answer is not one of the options, or not within the range then we throw exception
        if (questionDto.getQuestionType().equals("single_choice") ||
                questionDto.getQuestionType().equals("single_choice_conditional")) {
            if (!questionDto.getOptions().contains(answerDto.getAnswerText())) {
                throw new BusinessException("Your answer should be one of those: " + questionDto.getOptions());
            }

        } else if (questionDto.getQuestionType().equals("number_range")) {
            try {
                int value = Integer.parseInt(answerDto.getAnswerText());
                int minValue = Integer.parseInt(questionDto.getOptions().get(0));
                int maxValue = Integer.parseInt(questionDto.getOptions().get(1));
                if (value < minValue || value > maxValue) {
                    throw new BusinessException("Your answer should be between " + minValue + " and " + maxValue);
                }
            } catch (NumberFormatException e) {
                throw new BusinessException("Your answer should be a numeric value");
            }
        }
    }


}
