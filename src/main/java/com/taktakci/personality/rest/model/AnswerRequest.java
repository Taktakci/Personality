package com.taktakci.personality.rest.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnswerRequest {
    String userId;
    Integer questionId;
    String answerText;
}
