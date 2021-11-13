package com.taktakci.personality.rest.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnswerResponse {
    Integer id;
    String userId;
    Integer questionId;
    String answerText;
    Integer conditionalQuestionId;
}
