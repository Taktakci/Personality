package com.taktakci.personality.repository.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "QUESTION")
@Getter
@Setter
public class Question {
    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @Column(name="QUESTION_TEXT")
    private String questionText;

    @ManyToOne
    private QuestionCategory category;

    @ManyToOne
    private QuestionType questionType;

    @Column(name="IS_CONDITIONAL")
    private Boolean isConditional;

    @Column(name="OPTIONS")
    private String options;

    @Column(name="EXPECTED_ANSWER")
    private String expectedAnswer;

    @Column(name="COND_QUEST_ID")
    private Integer conditionalQuestionId;
}
