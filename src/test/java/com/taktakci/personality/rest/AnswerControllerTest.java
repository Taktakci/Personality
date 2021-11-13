package com.taktakci.personality.rest;

import com.taktakci.personality.rest.mapper.AnswerControllerMapper;
import com.taktakci.personality.rest.model.AnswerRequest;
import com.taktakci.personality.rest.model.AnswerResponse;
import com.taktakci.personality.service.AnswerService;
import com.taktakci.personality.service.model.AnswerDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AnswerControllerTest {

    @InjectMocks
    private AnswerController answerController;

    @Mock
    private AnswerService answerService;

    @Mock
    private AnswerControllerMapper mapper;

    @Test
    void answerQuestionSuccessful() {

        //mock answerService and mapper
        AnswerRequest answerRequest = new AnswerRequest();
        answerRequest.setUserId("usr1");
        AnswerDto answerDto = new AnswerDto();
        answerDto.setUserId("usr1");
        when(mapper.toAnswerDto(answerRequest))
                .thenReturn(answerDto);
        AnswerResponse answerResponse = new AnswerResponse();
        answerResponse.setUserId("usr1");
        when(answerService.answerQuestion(answerDto))
                .thenReturn(answerDto);
        when(mapper.toAnswerResponse(answerDto))
                .thenReturn(answerResponse);

        ResponseEntity<AnswerResponse> response = answerController.answerQuestion(answerRequest);

        Assertions.assertEquals("usr1", response.getBody().getUserId());
    }

    @Test
    void getUserAnswerSuccessful() {

        //mock answerService and mapper
        AnswerDto answerDto = new AnswerDto();
        answerDto.setUserId("usr1");
        when(answerService.getUserAnswer("usr1", 5))
                .thenReturn(answerDto);
        AnswerResponse answerResponse = new AnswerResponse();
        answerResponse.setUserId("usr1");
        when(mapper.toAnswerResponse(answerDto))
                .thenReturn(answerResponse);

        ResponseEntity<AnswerResponse> response = answerController.getUserAnswer("usr1", 5);

        Assertions.assertEquals("usr1", response.getBody().getUserId());
    }

    @Test
    void getUserAnswersSuccessful() {

        //mock answerService and mapper
        AnswerDto answerDto = new AnswerDto();
        answerDto.setUserId("usr1");
        List<AnswerDto> answerDtoList = Collections.singletonList(answerDto);
        when(answerService.getUserAnswers("usr1"))
                .thenReturn(answerDtoList);
        AnswerResponse answerResponse = new AnswerResponse();
        answerResponse.setUserId("usr1");
        List<AnswerResponse> answerResponseList = Collections.singletonList(answerResponse);
        when(mapper.toAnswerResponseList(answerDtoList))
                .thenReturn(answerResponseList);

        ResponseEntity<List<AnswerResponse>> response = answerController.getUserAnswers("usr1");

        Assertions.assertEquals("usr1", response.getBody().get(0).getUserId());
    }
}