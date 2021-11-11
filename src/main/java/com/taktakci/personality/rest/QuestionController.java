package com.taktakci.personality.rest;

import com.taktakci.personality.rest.mapper.QuestionControllerMapper;
import com.taktakci.personality.rest.model.CategoryResponse;
import com.taktakci.personality.rest.model.QuestionIdListResponse;
import com.taktakci.personality.rest.model.QuestionResponse;
import com.taktakci.personality.service.QuestionService;
import com.taktakci.personality.service.model.QuestionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {

    private final QuestionService questionService;
    private final QuestionControllerMapper mapper;

    @Autowired
    public QuestionController(QuestionService questionService, QuestionControllerMapper mapper) {
        this.questionService = questionService;
        this.mapper = mapper;
    }

    @GetMapping("/{questionId}")
    public ResponseEntity<QuestionResponse> getQuestionById(@PathVariable int questionId) {
        QuestionDto questionDto = questionService.getQuestionById(questionId);
        QuestionResponse response = mapper.toQuestionResponse(questionDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/categoryId/{categoryId}")
    public ResponseEntity<QuestionIdListResponse> getQuestionIdListByCategoryId(@PathVariable String categoryId) {
        List<Integer> idList = questionService.getQuestionIdListByCategoryId(categoryId);
        QuestionIdListResponse response = mapper.toQuestionIdListResponse(idList);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/category")
    public ResponseEntity<CategoryResponse> getQuestionCategories() {
        List<String> categoryList = questionService.getQuestionCategories();
        CategoryResponse response = mapper.toCategoryResponse(categoryList);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }
}
