package com.taktakci.personality.service;

import com.taktakci.personality.repository.QuestionRepository;
import com.taktakci.personality.repository.entity.Question;
import com.taktakci.personality.service.exceptions.BusinessException;
import com.taktakci.personality.service.mapper.QuestionServiceMapper;
import com.taktakci.personality.service.model.QuestionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final QuestionServiceMapper questionServiceMapper;

    @Autowired
    public QuestionService(QuestionRepository questionRepository, QuestionServiceMapper questionServiceMapper) {
        this.questionRepository = questionRepository;
        this.questionServiceMapper = questionServiceMapper;
    }

    public QuestionDto getQuestionById(int questionId) {
        Optional<Question> optionalQuestion = questionRepository.findById(questionId);
        return questionServiceMapper.toQuestionDto(
                optionalQuestion.orElseThrow(() -> new BusinessException("Question Not Found for id " + questionId)));
    }

    public List<String> getQuestionCategories() {
        return questionRepository.findCategoryList();
    }

    public List<Integer> getQuestionIdListByCategoryId(String categoryId) {
        return questionRepository.findByCategoryId(categoryId);
    }
}
