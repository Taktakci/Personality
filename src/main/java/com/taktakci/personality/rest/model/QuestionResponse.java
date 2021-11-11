package com.taktakci.personality.rest.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class QuestionResponse {
    Integer id;
    String questionText;
    String category;
    String questionType;
    List<String> options;
    String expectedAnswer;
    String conditionalQuestionId;
}
