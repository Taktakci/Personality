package com.taktakci.personality.rest;

import com.taktakci.personality.rest.mapper.AnswerControllerMapper;
import com.taktakci.personality.rest.model.AnswerRequest;
import com.taktakci.personality.rest.model.AnswerResponse;
import com.taktakci.personality.service.AnswerService;
import com.taktakci.personality.service.model.AnswerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/answer")
public class AnswerController {

    private final AnswerService answerService;
    private final AnswerControllerMapper mapper;

    @Autowired
    public AnswerController(AnswerService answerService, AnswerControllerMapper mapper) {
        this.answerService = answerService;
        this.mapper = mapper;
    }

    @PostMapping("")
    public ResponseEntity<AnswerResponse> answerQuestion(@RequestBody AnswerRequest request) {
        AnswerDto answerDto = mapper.toAnswerDto(request);
        answerDto = answerService.answerQuestion(answerDto);
        AnswerResponse response = mapper.toAnswerResponse(answerDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/userId/{userId}/questionId/{questionId}")
    public ResponseEntity<AnswerResponse> getUserAnswer(@PathVariable String userId,
                                                        @PathVariable Integer questionId) {
        AnswerDto answerDto = answerService.getUserAnswer(userId, questionId);
        AnswerResponse response = mapper.toAnswerResponse(answerDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/userId/{userId}")
    public ResponseEntity<List<AnswerResponse>> getUserAnswers(@PathVariable String userId) {
        List<AnswerDto> answerDtoList = answerService.getUserAnswers(userId);
        List<AnswerResponse> response = mapper.toAnswerResponseList(answerDtoList);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
