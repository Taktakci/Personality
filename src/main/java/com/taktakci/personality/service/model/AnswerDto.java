package com.taktakci.personality.service.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnswerDto {
    Integer id;
    String userId;
    Integer questionId;
    String answerText;
    Integer conditionalQuestionId;
}
