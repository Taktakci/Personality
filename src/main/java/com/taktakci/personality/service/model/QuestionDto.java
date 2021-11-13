package com.taktakci.personality.service.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class QuestionDto {
    Integer id;
    String questionText;
    String category;
    String questionType;
    List<String> options;
    String expectedAnswer;
    Integer conditionalQuestionId;
}
